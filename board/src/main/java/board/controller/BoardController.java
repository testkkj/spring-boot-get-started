package board.controller;

import java.util.List;

import board.dto.BoardDto;
import board.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception {
		log.debug("openBoardList");
		final ModelAndView mv = new ModelAndView("/board/boardList");

		final List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list", list);

		return mv;
	}

	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception {
		return "/board/boardWrite";
	}

	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(final BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest)
			throws Exception {
		boardService.insertBoard(board, multipartHttpServletRequest);
		return "redirect:/board/openBoardList.do";
	}

	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam final int boardIdx) throws Exception {
		final ModelAndView mv = new ModelAndView("/board/boardDetail");

		final BoardDto board = boardService.selectBoardDetail(boardIdx);
		mv.addObject("board", board);

		return mv;
	}

	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(final BoardDto board) throws Exception {
		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
	}

	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(final int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/openBoardList.do";
	}
}
