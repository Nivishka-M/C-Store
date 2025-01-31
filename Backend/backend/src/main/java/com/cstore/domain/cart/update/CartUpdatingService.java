package com.cstore.domain.cart.update;

import com.cstore.dao.cart.CartDao;
import com.cstore.dao.variant.VariantDao;
import com.cstore.dto.CartItem_;
import com.cstore.exception.NoSuchVariantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartUpdatingService {
    private final CartDao cartDao;
    private final VariantDao variantDao;

    @Autowired
    public CartUpdatingService(CartDao cartDao, VariantDao variantDao) {
        this.cartDao = cartDao;
        this.variantDao = variantDao;
    }

    public Long addVariant(Long userId, CartItem_ cartItem_) {
        return null;
        /*List<Long> propertyIds = cartItem_.getPropertyIds();
        Set<Long> variantIds = new HashSet<Long>();

        for (Long propertyId : propertyIds) {
            List<Variant> variants = variantDao.findByProperty(propertyId);
            for (Variant variant : variants) {
                variantIds.add(variant.getVariantId());
            }
        }

        if (variantIds.size() != 1) {
            throw new NoSuchVariantException("No unique variant with the given set of properties found.");
        }
        Long variantId = variantIds.iterator().next();

        Integer stockCount = variantDao.countStocks(variantId);
        if (stockCount < cartItem_.getQuantity()) {
            throw new SparseStocksException("Only " + stockCount + " stocks available.");
        }

        int status = 0;
        while (status == 0) {
            status = cartDao.addToCart(userId, variantId, cartItem_.getQuantity());
        }

        return variantId;*/
    }

    public void removeVariant(Long userId, Long variantId) {
        if (cartDao.removeFromCart(userId, variantId) != 1) {
            throw new NoSuchVariantException("User with id " + userId + " has no variant of id " + variantId + " in his cart.");
        }
    }
}
