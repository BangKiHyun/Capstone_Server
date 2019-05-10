package com.bang.ds_alcohol_project.service;

import com.bang.ds_alcohol_project.mapper.UserMapper;
import com.bang.ds_alcohol_project.model.DefaultRes;
import com.bang.ds_alcohol_project.utils.PyUtill;
import com.bang.ds_alcohol_project.utils.ResponseMessage;
import com.bang.ds_alcohol_project.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PyService {

    private final UserMapper userMapper;
    private static final String VOICE = "voice.py";
    private static final String IMAGE = "drunken.py";

    public PyService(UserMapper userMapper) {

        this.userMapper = userMapper;
    }

    public DefaultRes learn() {
        int result1 = -1;
        int result2 = -1;
        PyUtill pyUtill = new PyUtill();

        String command = "python image.py " + "매개변수";
        result1 = pyUtill.execCommand(command);

        if (result1 != -1) {
            command = "python voice.py" + "매개변수";
            result2 = pyUtill.execCommand(command);
        }

        if (result1 == 1) {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.NO_ALCOHOL);
        } else {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.ALCOHOL);
        }
    }

/*    public DefaultRes learnVoice() {
        int result = 0;
        PyUtill pyUtill = new PyUtill();

        //이 매개변수는 S3에서 가져오면되는건감?\
        String command = "python" + VOICE + "매개변수";
        result = pyUtill.execCommand(command);
        log.info("음성 값 :" + result);

        if (result == 1) {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.NO_ALCOHOL);
        } else {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.ALCOHOL);
        }
    }


    public DefaultRes learnImage() {
        int result = 0;
        PyUtill pyUtill = new PyUtill();
        //"D:/DrunkenDeepLearning/drunken.py D:/DrunkenDeepLearning/image/hee.jpg"
        String command = "python D:/DrunkenDeepLearning/drunken.py hee.jpg";
        result = pyUtill.execCommand(command);

        if (result == 1) {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.NO_ALCOHOL);
        } else {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.ALCOHOL);
        }
    }*/
}
