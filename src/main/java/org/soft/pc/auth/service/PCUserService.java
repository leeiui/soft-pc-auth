package org.soft.pc.auth.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.soft.pc.auth.mapper.MbgRoleMapper;
import org.soft.pc.auth.mapper.MbgUserMapper;
import org.soft.pc.auth.mapper.UserAndRoleMapper;
import org.soft.pc.auth.model.MbgUser;
import org.soft.pc.auth.model.MbgUserExample;
import org.soft.pc.auth.model.UserAndRole;
import org.soft.pc.auth.model.UserUpdateFormModel;
import org.soft.pc.auth.uenum.RoleEnum;
import org.soft.pc.auth.uenum.StateEnum;
import org.soft.pc.mgt.common.SecurityUtil;
import org.soft.pc.mgt.common.SoftJsonUtil;
import org.soft.pc.mgt.common.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value="transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
public class PCUserService {

	@Autowired
	private MbgUserMapper mbgUserMapper;
	@Autowired
	private MbgRoleMapper mbgRoleMapper;
	@Autowired
	private UserAndRoleMapper userAndRoleMapper;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Value("${USER_ID_REDIS}")
	private String USER_ID_REDIS;
	public void addUser(String username, String passwd) throws NoSuchAlgorithmException {

		MbgUser mbgUser = new MbgUser();
		mbgUser.setUsername(username);
		mbgUser.setPasswd(SecurityUtil.md5(passwd));
		mbgUser.setCreateTime(new Date());
		mbgUser.setState(StateEnum.INACTIVE.getCode());
		mbgUser.setRoleId(Integer.parseInt(RoleEnum.STAFF.getCode()));
		mbgUserMapper.insert(mbgUser);
	}
	public List<UserAndRole> getAllUsers() {
		
		return userAndRoleMapper.getUsersAndRoles();
	}
	public UserUpdateFormModel getUserUpdateForm(String uid) {
		UserUpdateFormModel userUpdateFormModel = new UserUpdateFormModel();
		//通过id查询user
		MbgUser mbgUser = mbgUserMapper.selectByPrimaryKey(Integer.parseInt(uid));
		userUpdateFormModel.setMbgUser(mbgUser);
		//从数据库中取出所有的角色
		userUpdateFormModel.setMbgRole(mbgRoleMapper.selectByExample(null));
		//遍历state 的枚举取出所有的状态
		List<String> states = new ArrayList<>();
		for(StateEnum sm : StateEnum.values()) {
			states.add(sm.getCode());
		}
		userUpdateFormModel.setStates(states);
		return userUpdateFormModel;
	}
	public void updateUser(MbgUser mbgUser) {

		mbgUserMapper.updateByPrimaryKeySelective(mbgUser);
	}
	public String login(String username, String passwd) throws NoSuchAlgorithmException {
		MbgUserExample userExa = new MbgUserExample();
		MbgUserExample.Criteria userCri = userExa.createCriteria();
		userCri.andUsernameEqualTo(username);
		List<MbgUser> uList = mbgUserMapper.selectByExample(userExa);
		MbgUser mbgUser = uList.get(0);
		if(mbgUser != null && SecurityUtil.md5(passwd).equals(mbgUser.getPasswd()) && mbgUser.getState().equals(StateEnum.ACTIVE.getCode())) {
			String uid = UUIDUtil.UUIDGenerator();
			mbgUser.setPasswd(null);
			String userJson = SoftJsonUtil.objectToJson(mbgUser);
			stringRedisTemplate.opsForValue().set(USER_ID_REDIS + ":" + uid, userJson, 15, TimeUnit.MINUTES);
			return USER_ID_REDIS+":" + uid;
		}
		return null;
	}
	public String getUserByToken(String token) {
		if(stringRedisTemplate.hasKey(token)) {
			stringRedisTemplate.expire(token, 15, TimeUnit.MINUTES);
			return stringRedisTemplate.opsForValue().get(token);
		}
		return null;
	}
	public void logout(String token) {

		stringRedisTemplate.delete(token);
	}

}
