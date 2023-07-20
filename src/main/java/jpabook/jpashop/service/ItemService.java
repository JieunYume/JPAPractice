package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // jpa가 성능을 최적화해줌, '이건 읽기 전용이야' 읽기 전용이 많으면 여기에 넣어주고, 읽기 전용이 아닌 메소드에 @Transactional 달아줌
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional // @Transactional(readOnly = false)
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional // merge 쓰지말고 변경 감지 기능을 사용해야 한다.
    public void updateItem(Long itemId,  String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);
        // findItem.change(price, name, stockQuantity); 의미 있는 메소드를 사용해서 값을 변경해야 한다

        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);

        // transactional에 의해 push됨 -> 데이터가 변경됨
    }

    // 회원 전체 조회
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
