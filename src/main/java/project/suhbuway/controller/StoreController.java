package project.suhbuway.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project.suhbuway.dto.Store;
import project.suhbuway.service.OrderService;
import project.suhbuway.service.StoreService;
import project.suhbuway.wrapper.OrderListWrapper;

/**
 * 매장
 */
@Controller
public class StoreController {
	@Autowired
	private StoreService service;
	@Autowired
	private OrderService orderService;

	/**
	 *  로그인폼
	 */
	@RequestMapping("/store/login")
	public void login() {}

	/**
	 *  로그인 실패: FailHandler의 tiles 적용위한 controller
	 */
	@RequestMapping("/errorLogin")
	public ModelAndView test(HttpServletRequest request) {
		String errorMessage =(String)request.getAttribute("errorMessage");
		return new ModelAndView("store/login" , "errorMessage" ,errorMessage );
	}

	/**
	 *  회원가입폼
	 */
	@RequestMapping("/store/register")
	public void registerForm() {}

	/**
	 * 아이디 중복확인 Ajax
	 */
	@RequestMapping("/idcheckAjax")
	@ResponseBody   
	public String idCheckAjax(HttpServletRequest request) {
		//System.out.println("컨트롤러 id체크 : " + request.getParameter("storeId") );
		return service.idcheck(request.getParameter("storeId"));
	}
	
	/**
	 *  회원가입하기
	 */
	@RequestMapping("/storeRegister")
	public String register( Store store ) {
		service.joinStore(store);
		return "index";  
	}
	
	/**
	 * 리스트 정보 뽑아오기 ajax
	 */
	@RequestMapping("/getOrderList")
	@ResponseBody
	public List<OrderListWrapper> orderList(String storeId) {
		List<OrderListWrapper> list = orderService.selectOrderListByStore(storeId);
		for(OrderListWrapper item : list)
			System.out.println(item);
		return list;
	}
	
	/**
	 * 매장별 주문 갯수 정보 뽑아오기 ajax 
	 */
	@RequestMapping("/getOrderListNumber")
	@ResponseBody
	public int orderListNumber(String storeId) {
		return orderService.getOrderListNumber(storeId);
	}
}
