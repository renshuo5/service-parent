package com.renshuo.cloud.common.handler;

import com.google.common.collect.Lists;
import com.renshuo.cloud.common.exception.BusinessException;
import com.renshuo.cloud.common.model.ResultErrorMsg;
import com.renshuo.cloud.common.model.ResultMsg;
import com.renshuo.cloud.enums.ResultStatus;
import jodd.exception.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 只获取包含renshuo的异常信息
     *
     * @param e
     * @return
     */
    private String getExcetionOfBitnei(Exception e) {
        String detailExceptionMessage = ExceptionUtil.exceptionChainToString(e);
        // 按行分割
        String[] lines = detailExceptionMessage.split("\n");
        List<String> messages = Lists.newArrayList();
        if (lines.length > 2) {
            messages.add(lines[0]);
            messages.add(lines[1]);
        }
        for (int i = 2; i < lines.length; i++) {
            if (StringUtils.isNotEmpty(lines[i]) && lines[i].contains("renshuo")) {
                messages.add(lines[i]);
            }
        }
        String exceptionBody = null;
        if (messages.isEmpty()) {
            exceptionBody = detailExceptionMessage;
        } else {
            exceptionBody = StringUtils.join(messages, "\n");
        }
        return exceptionBody;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultMsg exceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("error", e);
        final String detailExceptionMessage = getExcetionOfBitnei(e);
        ResultErrorMsg msg = new ResultErrorMsg();
        //暂时不处理此字段
        msg.setErrorCode("-2");
        msg.setCode(ResultStatus.CODE_EXCEPTION.getCode());
        msg.setMsg(ResultStatus.CODE_EXCEPTION.getMessage());
        msg.setDetailException(detailExceptionMessage);
        return msg;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultMsg AppBeanExceptionHandler(HttpServletRequest req, BusinessException e) throws Exception {
        return new ResultMsg(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResultMsg maxUploadSizeExceededExceptionHandler(HttpServletRequest req, MaxUploadSizeExceededException e) {
        return ResultMsg.fail("上传文件过大");
    }

}
