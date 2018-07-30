package com.elife.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elife.commands.ProductForm;
import com.elife.converters.ProductToProductForm;
import com.elife.domain.Product;
import com.elife.services.ProductService;

import javax.validation.Valid;

@RestController
public class ProductController {
	private ProductService productService;

	private ProductToProductForm productToProductForm;

	@Autowired
	public void setProductToProductForm(ProductToProductForm productToProductForm) {
		this.productToProductForm = productToProductForm;
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping("/")
	public String redirToList() {
		return "redirect:/product/list";
	}

	@RequestMapping(value = { "/product/list", "/product" }, method = RequestMethod.GET)
	public String listProducts(Model model) {
		model.addAttribute("products", productService.listAll());
		return "product/list";
	}

	@RequestMapping(value = "/product/show/{id}", method = RequestMethod.GET)
	public String getProduct(@PathVariable String id, Model model) {
		model.addAttribute("product", productService.getById(Long.valueOf(id)));
		return "product/show";
	}

	@RequestMapping(value = "/product/edit/{id}", method = RequestMethod.PUT)
	public String edit(@PathVariable String id, Model model) {
		Product product = productService.getById(Long.valueOf(id));
		ProductForm productForm = productToProductForm.convert(product);

		model.addAttribute("productForm", productForm);
		return "product/productform";
	}

	@RequestMapping(value = "/product/new", method = RequestMethod.POST)
	public String newProduct(Model model) {
		model.addAttribute("productForm", new ProductForm());
		return "product/productform";
	}

	@RequestMapping(value = "/product/save", method = RequestMethod.POST)
	public String saveOrUpdateProduct(@RequestBody ProductForm productForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "product/productform";
		}

		Product savedProduct = productService.saveOrUpdateProductForm(productForm);

		return "redirect:/product/show/" + savedProduct.getId();
	}

	@RequestMapping(value = "/product/delete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String id) {
		productService.delete(Long.valueOf(id));
		return "redirect:/product/list";
	}
}
