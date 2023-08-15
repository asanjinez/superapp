package com.superapp.cart;

import com.superapp.exception.NoCartFoundException;
import com.superapp.exception.NoPersonFoundException;
import com.superapp.persona.Person;
import com.superapp.product.IProductMapper;
import com.superapp.product.ProductDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class CartServiceImpl implements ICartService{
    @Autowired
    private ICartJpaDao cartJpaDao;
    @Autowired
    private IProductMapper productMapper;
    /*@Autowired
    private ICartMapper cartMapper;
    */
    @Override
    public Cart addItem(Integer idCart, ProductDto productDto, Float quantity) {
        Cart cart = this.cartExists(idCart);
        cart.addItem(productMapper.productDtoToProduct(productDto), quantity);
        cartJpaDao.save(cart);
        return cart;
    }

    @Override
    public Cart deleteItem(Integer idCart, Integer idItem) {
        return null;
    }

    @Override
    public Cart deleteAllItems(Integer idCart) {
        return null;
    }

    private Cart cartExists(Integer id){
        Optional<Cart> cart = cartJpaDao.findById(id);
        if (!cart.isPresent())
            throw new NoCartFoundException("This cart doesn't exist");

        return cart.get();
    }
}
