package com.superapp.cart;

import com.superapp.exception.NoCartFoundException;
import com.superapp.person.IPersonMapper;
import com.superapp.person.PersonDto;
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
    @Autowired
    private IPersonMapper personMapper;

    @Override
    public Cart createCart(PersonDto person){
        Cart cart = new Cart(personMapper.personDtoToPerson(person));
        return cartJpaDao.save(cart);
    }

    @Override
    public Cart addItem(Integer idCart, ProductDto productDto, Float quantity) {
        Cart cart = this.cartExists(idCart);
        cart.addItem(productMapper.productDtoToProduct(productDto), quantity);
        return cartJpaDao.save(cart);
    }

    @Override
    public Cart deleteItem(Integer idCart, Integer idItem) {
        Cart cart = this.cartExists(idCart);
        cart.removeItem(idItem);
        return cartJpaDao.save(cart);
    }

    @Override
    public Cart deleteAllItems(Integer idCart) {
        Cart cart = this.cartExists(idCart);
        cart.removeAllItems();
        return cartJpaDao.save(cart);
    }

    @Override
    public Cart getCart(Integer idPerson) {
        Cart cart = cartJpaDao.findByPerson(idPerson);
        if(cart==null)
            throw new NoCartFoundException("This cart doesn't exist");
        return cart;
    }

    private Cart cartExists(Integer id){
        Optional<Cart> cart = cartJpaDao.findById(id);
        if (!cart.isPresent())
            throw new NoCartFoundException("This cart doesn't exist");

        return cart.get();
    }
}
