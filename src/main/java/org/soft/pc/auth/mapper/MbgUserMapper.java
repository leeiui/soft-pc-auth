package org.soft.pc.auth.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.soft.pc.auth.model.MbgUser;
import org.soft.pc.auth.model.MbgUserExample;

public interface MbgUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    long countByExample(MbgUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int deleteByExample(MbgUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int insert(MbgUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int insertSelective(MbgUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    List<MbgUser> selectByExample(MbgUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    MbgUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int updateByExampleSelective(@Param("record") MbgUser record, @Param("example") MbgUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int updateByExample(@Param("record") MbgUser record, @Param("example") MbgUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int updateByPrimaryKeySelective(MbgUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int updateByPrimaryKey(MbgUser record);
}