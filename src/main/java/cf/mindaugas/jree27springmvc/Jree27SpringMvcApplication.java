package cf.mindaugas.jree27springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Jree27SpringMvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(Jree27SpringMvcApplication.class, args);
    }
}

@Controller
class HelloController {
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/hello")
    public String sayHello(Model model, @RequestParam(required = false) String name){
        model.addAttribute("name_in_view", name);
        System.out.println(model);
        return "hello";
    }

    @RequestMapping("/calculator")
    public String calculate(
            Model model,
            @RequestParam(required = false) String var1,
            @RequestParam(required = false) String var2,
            @RequestParam(required = false) String sign
    ){
        System.out.println(">>>" + sign + "<<<");
        Integer res = null;
        if(sign.equals("+"))
            res = Integer.parseInt(var1) + Integer.parseInt(var2);
        else if(sign.equals("-"))
            res = Integer.parseInt(var1) - Integer.parseInt(var2);

        model.addAttribute("var1", var1);
        model.addAttribute("var2", var1);
        model.addAttribute("sign", sign);
        model.addAttribute("res", res);
        return "hello";
    }

    @RequestMapping("/goodbye")
    public String sayHello(Model model){
        return "goodbye";
    }

    @RequestMapping("/products")
    public String renderProducts(Model model){
        model.addAttribute("products", productRepository.getProducts());
        return "products";
    }
}

@Repository
class ProductRepository {
    private List<Product> products = new ArrayList<>(){{
        add(new Product(1L, "Slippers", 55.55f));
        add(new Product(2L, "Pants", 65.99f));
        add(new Product(3L, "Panda", 165.99f));
    }};

    public List<Product> getProducts(){
        return products;
    }
}

class Product {
    private Long id;
    private String name;
    private Float price;

    public Product(Long id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }
}