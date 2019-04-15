package com.cn.demo.mapstruct;

import com.cn.demo.pojo.dto.vo.UserVO;
import com.cn.demo.pojo.entity.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author yc
 */
@Mapper
public interface UserMapStruct {

    UserMapStruct INSTANCE = Mappers.getMapper(UserMapStruct.class);

    /**
     * vo实体转数据库实体
     *
     * @param userVO vo实体
     * @return 数据库实体
     */
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserDO voToDo(UserVO userVO);

    /**
     * vo实体转数据库实体
     *
     * @param userVOList vo实体
     * @return 数据库实体
     */
    List<UserDO> voToDo(List<UserVO> userVOList);

    /**
     * 数据库实体转vo实体
     *
     * @param userDO 数据库实体
     * @return vo实体
     */
    UserVO doToVo(UserDO userDO);

    /**
     * 数据库实体转vo实体
     *
     * @param userDOList 数据库实体
     * @return vo实体
     */
    List<UserVO> doToVo(List<UserDO> userDOList);

}
