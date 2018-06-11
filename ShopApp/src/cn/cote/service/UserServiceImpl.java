package cn.cote.service;

import cn.cote.mapper.UserMapper;
import cn.cote.pojo.User;
import cn.cote.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserMessage(User this_user) {
      UserExample example = new UserExample();
      UserExample.Criteria criteria =example.createCriteria();
      criteria.andUserNameEqualTo(this_user.getUserName()).andUserPasswordEqualTo(this_user.getUserPassword());
      List<User> user=userMapper.selectByExample(example);
      if(user.size()!=0){
          return user.get(0);
      }else{
          return null;
      }
    }

    @Override
    public int insertUser(User this_user) {
        int code = 0;
//      创建条件类
       UserExample example = new UserExample();
//      生成条件类
       UserExample.Criteria criteria = example.createCriteria();
//      创建条件
       criteria.andUserNameEqualTo(this_user.getUserName());
       List<User> userExample=userMapper.selectByExample(example);
       int number = userExample.size();
//       判断用户是否存在
       if(number>0){
           code = 0;
       }
       else if(number==0){
           //设置默认的用户名和头像 ，然后创建该用户
           this_user.setUserNc("小跳");
           this_user.setUserImg("img/userImg/toux.jpg");
           userMapper.insert(this_user);
           code = 1;
       }else{
           code = -1;
       }
       return code;
    }

    @Override
    public int changeRegisterNc(String this_userNc,int this_id) {
      User this_user;
      this_user = userMapper.selectByPrimaryKey(this_id);
      this_user.setUserNc(this_userNc);
      int code = userMapper.updateByPrimaryKey(this_user);
      return code;
    }

    @Override
    public int addUserImg(String newFileName,int this_id) {
        User this_user;
        this_user = userMapper.selectByPrimaryKey(this_id);
        this_user.setUserImg("/img/userImg/"+newFileName);
        int code = userMapper.updateByPrimaryKey(this_user);
        return code;
    }
}
