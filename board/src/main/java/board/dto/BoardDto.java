package board.dto;

import lombok.Data;

@Data
/*
 * lombok의 어노테이션으로 모든 필드의 getter/setter를 생성하고 tostring,hashcode/equals 메소드도 생성
 * setter의 경우 final이 아닌 필드만 생성
 */
public class BoardDto {
	private int boardIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private String creatorId;
	private String createdDatetime;
	private String updaterId;
	private String updateDatetime;
}
