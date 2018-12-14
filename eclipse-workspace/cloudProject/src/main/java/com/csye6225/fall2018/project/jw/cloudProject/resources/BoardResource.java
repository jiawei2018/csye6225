package com.csye6225.fall2018.project.jw.cloudProject.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Board;
import com.csye6225.fall2018.project.jw.cloudProject.service.BoardService;

@Path("boards")
public class BoardResource {

BoardService boardService = new BoardService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Board> showAll(){
	    return boardService.getAllBoards();
	}
	
	@GET
	@Path("/allboards")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Board> getAllBoards(){
	    return boardService.getAllBoards();
	}
 
	@GET
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board getBoard(@PathParam("boardId") String boardId) {
		return boardService.getBoard(boardId);
	}
	
	@DELETE
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board deleteBoard(@PathParam("boardId") String boardId) {
		return boardService.deleteBoard(boardId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board addBoard(Board board) {
			return boardService.addBoard(board);
	}
	
	@PUT
	@Path("/{boardId}/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board addBoard(@PathParam("boardId") String boardId, 
			@PathParam("courseId")String courseId) {
		return boardService.modifyBoardCourseId(boardId, courseId);//(boardId, courseId);
	}
	
//	@DELETE
//	@Path("/{boardId}/{courseId}")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Board removeCourse(@PathParam("boardId") String boardId, 
//			@PathParam("courseId")String courseId) {
//		return boardService.removeCourseFromBoard(boardId, courseId);
//	}
//	
	

}
