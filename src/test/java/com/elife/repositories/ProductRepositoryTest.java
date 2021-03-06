package com.elife.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.elife.domain.Product;
import com.elife.repositories.ProductRepository;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    private static final BigDecimal BIG_DECIMAL_100 = BigDecimal.valueOf(200.00);
    private static final String PRODUCT_DESCRIPTION = "a cool lokes product";
    private static final String IMAGE_URL = "http://an-imageurl.com/image1.jpg";

    @Autowired
    private ProductRepository productRepository;

  /*  @Autowired
    private MockMvc mockMvc;*/
    
    
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        //given
        Product product = new Product();
//        product.setId(Long.valueOf(2));
        product.setDescription(PRODUCT_DESCRIPTION);
        product.setImageUrl(IMAGE_URL);
        product.setPrice(BIG_DECIMAL_100);

//        Mockito.when(productRepository.save(product)).then(answer)
        //when
        productRepository.save(product);

        //then
        Assert.assertNotNull(product.getId());
        Product newProduct = productRepository.findById(product.getId()).orElse(null);
        Assert.assertEquals((Long) 1L, newProduct.getId());
        Assert.assertEquals(PRODUCT_DESCRIPTION, newProduct.getDescription());
        Assert.assertEquals(BIG_DECIMAL_100.compareTo(newProduct.getPrice()), 0);
        Assert.assertEquals(IMAGE_URL, newProduct.getImageUrl());
    }
}