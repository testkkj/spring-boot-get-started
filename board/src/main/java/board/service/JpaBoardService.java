package board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.entity.JpaBoard;
import board.entity.JpaBoardFile;

public interface JpaBoardService {

    List<JpaBoard> selectBoardList() throws Exception;

    void saveBoard(JpaBoard jpaBoard, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    JpaBoard selectBoardDetail(int boardIdx, int idx) throws Exception;

    void deleteBoard(int boardIdx);

    JpaBoardFile selectBoardFileInformation(int boardIdx, int idx) throws Exception;
}