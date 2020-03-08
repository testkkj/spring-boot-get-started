package board.controller;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import board.common.FileUtils;
import board.entity.JpaBoard;
import board.entity.JpaBoardFile;
import board.service.JpaBoardService;

@Controller
public class JpaBoardController{

    @Autowired
    private JpaBoardService jpaBoardService;

    @RequestMapping(value = "/jpa/board", method = RequestMethod.GET)
    public ModelAndView openBoardList(ModelMap model) throws Exception{
        ModelAndView mv = new ModelAndView("/board/jpaBoardList");

        List<JpaBoard> list = jpaBoardService.selectBoardList();

        return mv;
    }

    @RequestMapping(value = "/jpa/board/write", method=RequestMethod.GET)
    public String openBoardWrite() throws Exception{
        return "/board/jpaBoardWrite";
    }

    @RequestMapping(value = "/jpa/board", method = RequestMethod.POST)
    public String writeBoard(JpaBoard jpaBoard, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
        JpaBoardService.saveBoard(jpaBoard, multipartHttpServletRequest);
        return "redirect:/jpa/board";
    }

    @RequestMapping(value = "/jpa/board/{boardIdx}", method = RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception{
        ModelAndView mv = new ModelAndView("/board/japBoardDetail");

        JpaBoard jpaBoard = jpaBoardService.selectBoardDetail(boardIdx);
        mv.addObject("jpaBoard", jpaBoard);

        return mv;
    }

    @RequestMapping(value = "/jpa/board{boardIdx}", method = RequestMethod.PUT)
    public String updateBoard(JpaBoard jpaBoard)throws Exception{
        jpaBoardService.saveBoard(jpaBoard, null);
        return "redirect:/jpa/board";
    }

    @RequestMapping(value = "jpa/board/{boardIdx}", method = RequestMethod.DELETE)
    public String deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
        jpaBoardService.deleteBoard(boardIdx);
        return "redirect:/jpa/board";
    }

    @RequestMapping(value = "jpa/board/file", method = RequestMethod.GET)
    public void downloadBoardFile(int boardIdx, int idx, HttpServletResponse response)
            throws Exception {
        JpaBoardFile jpaBoardFile = jpaBoardFile.selectBoardFileInformation(boardIdx, idx);
        
            byte[] files = FileUtils.readFileToByteArray(new File(files.getStoredFilePath()));

            response.setContentType("application/octet-stream");
            response.setContentLength(files.length);
            response.setHeader("Content-Disposition",
                    "attachment; fileName=\"" + URLEncoder.encode(jpaBoardFile.getOriginalFileName(), "UTF-8") + "\";");
            response.setHeader("Content-Transfer-Encoding", "binary");

            response.getOutputStream().write(files);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }
}