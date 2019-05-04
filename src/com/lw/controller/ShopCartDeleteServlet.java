package com.lw.controller;

import com.lw.entity.GoodsInfoDoMain;
import com.lw.entity.ShopCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/shopDelete.do")
public class ShopCartDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 用id查询 获取商品对应的信息
        String infoId = request.getParameter("id");
        int id = infoId == null ? 0 : Integer.parseInt(infoId);

        // 获取购物车对象
        HttpSession session = request.getSession();
        ShopCart shopCart = ShopCart.getShopCartObject(session);

        List<GoodsInfoDoMain> goodsInfoDoMains = shopCart.getGoodsInfoDoMains();
        for (int i = 0; i < goodsInfoDoMains.size(); i++) {
            if (goodsInfoDoMains.get(i).getId() == id) {
                goodsInfoDoMains.remove(goodsInfoDoMains.get(i));
            }
        }

        session.setAttribute("shopCart",shopCart);
        // 累计购物车的数量
        session.setAttribute("shop_count",shopCart.getTotalCount());

        // 获取总计
        session.setAttribute("totalPrice",shopCart.getTotaoPrice());

        request.getRequestDispatcher("WEB-INF/views/shopcat.jsp").forward(request,response);


    }
}