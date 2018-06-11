package cn.cote.service;

import cn.cote.pojo.User;

public interface UserService {
    /**
     * 寻找该用户的所有信息
     * @param this_user
     */
    User findUserMessage(User this_user);
    
    /**
     * 新用户注册
     * @param this_user
     */
    int insertUser(User this_user);

    /**
     * 修改用户昵称
     * @param this_userNc
     * @param this_id
     * @return
     */
    int changeRegisterNc(String this_userNc,int this_id);

    /**
     * 修改用户头像
     * @param newFileName
     * @param this_id
     * @return
     */
    int addUserImg(String newFileName,int this_id);
}
