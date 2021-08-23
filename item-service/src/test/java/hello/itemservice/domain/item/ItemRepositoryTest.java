package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterMethod() {
        itemRepository.clear();
    }

    @Test
    void save() {
        //given
        Item item = new Item("ItemA", 10000, 10);
        //when
        Item save = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(save.getId());
        assertThat(save).isEqualTo(findItem);
    }
    @Test
    void findAll() {
        //given
        Item item1 = new Item("ItemA", 10000, 10);
        Item item2 = new Item("ItemB", 50000, 5);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> all = itemRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(item1, item2);

    }

    @Test
    void update() {
        //given
        Item item1 = new Item("ItemA", 10000, 10);
        Item old = itemRepository.save(item1);
        Long savedId = old.getId();

        //when
        Item item2 = new Item("ItemB", 50000, 5);

        itemRepository.update(savedId, item2);

        //then
        Item findItem = itemRepository.findById(savedId);
        assertThat(findItem.getItemName()).isEqualTo(item2.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(item2.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(item2.getQuantity());

    }
}