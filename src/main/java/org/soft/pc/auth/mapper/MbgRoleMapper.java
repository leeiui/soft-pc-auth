package org.soft.pc.auth.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.soft.pc.auth.model.MbgRole;
import org.soft.pc.auth.model.MbgRoleExample;

public interface MbgRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    long countByExample(MbgRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int deleteByExample(MbgRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int insert(MbgRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int insertSelective(MbgRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    List<MbgRole> selectByExample(MbgRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    MbgRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int updateByExampleSelective(@Param("record") MbgRole record, @Param("example") MbgRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int updateByExample(@Param("record") MbgRole record, @Param("example") MbgRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int updateByPrimaryKeySelective(MbgRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated Thu Aug 30 09:26:37 CST 2018
     */
    int updateByPrimaryKey(MbgRole record);
}