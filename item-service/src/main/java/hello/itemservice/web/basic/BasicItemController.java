package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;


@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
@Slf4j
public class BasicItemController {

    private final ItemRepository itemRepository;


    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 1000, 10));
        itemRepository.save(new Item("itemB", 500, 5));
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping
    public String items(Model model) {
        List<Item> all = itemRepository.findAll();
        model.addAttribute("items", all);
        return "basic/items";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String save(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String save2(@ModelAttribute(name = "item") Item item,
                        Model model) {
        itemRepository.save(item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String save3(@ModelAttribute Item item, Model model) {
        itemRepository.save(item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String save4(Item item, Model model) {
        itemRepository.save(item); // ref 를 넘겨주는 방식이라서... 이게 id가 set되네
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String save5(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        // url에서 사용하지 않는 값은, 쿼리파라미터로 넘어간다.
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }
}
