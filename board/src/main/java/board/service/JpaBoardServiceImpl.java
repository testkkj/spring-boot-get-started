package board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.entity.JpaBoard;
import board.entity.JpaBoardFile;
import board.repository.JpaBoardRepository;
import board.common.FileUtils;

@Service
public class JpaBoardServiceImpl implements JpaBoardService {

    @Autowired
    JpaBoardRepository jpaBoardRepository;

    @Autowired
    FileUtils fileUtils;

    @Override
    public List<JpaBoard> selectBoardList() throws Exception {
        return jpaBoardRepository.findAllByOrderByBoardIdxDesc();
    }

    @Override
    public void saveBoard(JpaBoard jpaBoard, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        jpaBoard.setCreatorId("admin");
        List<JpaBoardFile> list = fileUtils.parseFileInfo(multipartHttpServletRequest);
        if (CollectionUtils.isEmpty(list) == false) {
            jpaBoard.setFileList(list);
        }
        jpaBoardRepository.save(jpaBoard);
    }

    @Override
    public JpaBoard selectBoardDetail(int boardIdx, int idx) throws Exception {
        Optional<JpaBoard> optional = jpaBoardRepository.findById(boardIdx);
        if (optional.isPresent()) {
            JpaBoard jpaBoard = optional.get();
            jpaBoard.setHitCnt(jpaBoard.getHitCnt() + 1);
            jpaBoardRepository.save(jpaBoard);

            return jpaBoard;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public void deleteBoard(int boardIdx) {
        jpaBoardRepository.deleteById(boardIdx);

    }

    @Override
    public JpaBoardFile selectBoardFileInformation(int boardIdx, int idx) throws Exception {
        JpaBoardFile jpaBoardFile = jpaBoardRepository.findBoardFile(boardIdx, idx);
        return jpaBoardFile;
    }

}