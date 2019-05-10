package com.bang.ds_alcohol_project.mapper;

import com.bang.ds_alcohol_project.dto.User;
import com.bang.ds_alcohol_project.model.SaveReq;
import com.bang.ds_alcohol_project.model.SignUpReq;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    //User 조회
    @Select("Select * From sys.mainUser Where userIdx = #{userIdx}")
    User findByUserIdx(@Param("userIdx") final int userIdx);

    //User 저장
    @Insert("INSERT INTO sys.mainUser(ID, password, name, gender, birth, phone, email, voiceFileUrl1, voiceFileUrl2, voiceFileUrl3, imageFileUrl) VALUES(" +
            "#{signUpReq.ID},#{signUpReq.password}, " +
            "#{signUpReq.name}, #{signUpReq.gender}, #{signUpReq.birth}, #{signUpReq.phone}, #{signUpReq.email}, " +
            "#{signUpReq.voiceFileUrl1}, #{signUpReq.voiceFileUrl2}, #{signUpReq.voiceFileUrl3}, #{signUpReq.imageFileUrl})")
    @Options(useGeneratedKeys = true, keyColumn = "sys.mainUser.userIdx")
    int saveUser(@Param("signUpReq") final SignUpReq signUpReq);


    //alcohol Data 저장
    @Insert("Insert into sys.alcoholData(userIdx, voiceFileUrl1, voiceFileUrl2, voiceFileUrl3, imageFileUrl) " +
            "Values (#{userIdx}, #{saveReq.voiceFileUrl1}, #{saveReq.voiceFileUrl2}, #{saveReq.voiceFileUrl3}, #{saveReq.imageFileUrl})")
    void saveAlcohol(@Param("saveReq") final SaveReq saveReq,
                     @Param("userIdx") final int userIdx);

    //alcohol Data 저장
    @Update("UPDATE sys.alcoholData SET voiceFileUrl1 = #{saveReq.voiceFileUrl1}, voiceFileUrl2 = #{saveReq.voiceFileUrl2}, voiceFileUrl3 = #{saveReq.voiceFileUrl3}, imageFileUrl = #{saveReq.imageFileUrl} WHERE userIdx = #{userIdx}")
    void updateAlcohol(@Param("saveReq") final SaveReq saveReq,
                       @Param("userIdx") final int userIdx);

    //alcohol Data 유무
    @Select("Select * From sys.alcoholData Where userIdx = #{userIdx}")
    User findByAlcoholData(@Param("userIdx") final int userIdx);


    //Init Data 저장
    /*@Insert("Insert into sys.initData(userIdx, voiceFileUrl1, voiceFileUrl2, voiceFileUrl3, imageFileUrl) " +
            "Values(#{userIdx}, #{saveReq.voiceFileUrl1}, #{saveReq.voiceFileUrl2}, #{saveReq.voiceFileUrl3}, #{saveReq.imageFileUrl})")
    void saveInitData(@Param("saveReq") final SaveReq saveReq,
                      @Param("userIdx") final int userIdx);*/

    //InitData 저장
    @Update("UPDATE sys.mainUser SET voiceFileUrl1 = #{saveReq.voiceFileUrl1}, voiceFileUrl2 = #{saveReq.voiceFileUrl2}, voiceFileUrl3 = #{saveReq.voiceFileUrl3}, imageFileUrl = #{saveReq.imageFileUrl} WHERE userIdx = #{userIdx}")
    void saveInitData(@Param("saveReq") final SaveReq saveReq,
                      @Param("userIdx") final int userIdx);


    //id 중복 chk
    @Select("Select * From sys.mainUser Where ID = #{ID}")
    User findByID(@Param("ID") final String ID);


    //이름과 비밀번호로 조회
    @Select("SELECT * FROM sys.mainUser WHERE ID = #{ID} AND password = #{password}")
    SignUpReq findByNameAndPassword(@Param("ID") final String ID, @Param("password") final String password);

    //초기 데이터 null값 검사
    @Select("Select * from sys.mainUser Where userIdx = #{userIdx} AND (voiceFileUrl1 IS NULL OR voiceFileUrl2 IS NULL OR voiceFileUrl3 IS NULL OR imageFileUrl IS NULL)")
    User chkFile(@Param("userIdx") final int userIdx);


    //result값 저장
    //@Insert("Insert into sys.initData Where voiceResult1 = #{voiceResult")
    //void saveResult1(@Param("voiceResult") final String Result);

    @Select("Select userIdx, voiceFIleUrl1, voiceFIleUrl2, voiceFileUrl3, ImageFileUrl From sys.mainUser Where userIdx = #{userIdx}")
    SignUpReq findByInitFile(@Param("userIdx") final int userIdx);


}
