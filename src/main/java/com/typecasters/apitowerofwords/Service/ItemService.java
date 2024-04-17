package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.ItemEntity;
import com.typecasters.apitowerofwords.Repository.ItemRepository;
import com.typecasters.apitowerofwords.Repository.UserItemRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository item_repo;

    // Create
    public ItemEntity insertItem(ItemEntity item) {
        return item_repo.save(item);
    }


    // Read
    public List<ItemEntity> getAllItems() {
        return item_repo.findAll();
    }

    public Optional<ItemEntity> getItemById(int item_id) {
        return item_repo.findById(item_id);
    }

    // Update
    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    public ItemEntity updateItem(int item_id, ItemEntity new_item_details){
        ItemEntity item = new ItemEntity();

        try {
            item = item_repo.findById(item_id).get();

            item.setItem_name(new_item_details.getItem_name());
            item.setItem_description(new_item_details.getItem_description());
            item.setItem_price(new_item_details.getItem_price());
            item.setImage_path(new_item_details.getImage_path());
        }catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Item " + item_id + " does not exist!");
        }finally {
            return item_repo.save(item);
        }
    }


    // Delete
    public String deleteItem(int item_id) {
        String msg = "";

        if(item_repo.findById(item_id).isPresent()) {
            item_repo.deleteById(item_id);

            msg = "Item " + item_id + " is successfully deleted!";
        }

        return msg;
    }

//    @Autowired
//    private UserRepository user_repo;
//
//    @Autowired
//    private UserItemRepository user_item_repo;
//
//
//
//
//    public ItemService(ItemRepository item_repo, UserRepository user_repo, UserItemRepository user_item_repo){
//            this.item_repo = item_repo;
//            this.user_repo = user_repo;
//            this.user_item_repo = user_item_repo;
//    }
//
//    public ItemService(ItemRepository item_repo){
//        this.item_repo = item_repo;
//    }
//
//    @Transactional
//    public ItemEntity addItemWithUserItems(ItemEntity item){
//        ItemEntity saved_item = item_repo.save(item);
//
//        List<UserEntity> users = user_repo.save(item);
//
//        for(UserEntity user : users){
//            UserItemEntity user_item = new UserItemEntity();
//            userItem.setItem(savedItem);
//            userItem.setUserDetails(user.getUserDetails());
//            user_item_repo.save(user_item);
//        }
//
//        return saved_item;
//    }
//
//    public List<ItemEntity> getAllItems() {
//        return item_repo.findAll();
//    }
//
//    public ItemEntity getItembyId(int item_id){
//        return item_repo.findById(item_id).orElse(null);
//    }
//
//    @Transactional
//    public ItemEntity updateItem(int item_id, ItemEntity updated_item){
//        ItemEntity existing_item = item_repo.findById(item_id).orElse(null);
//
//        if  (existing_item != null){
//
//            if (updated_item.getItem_name() != null){
//                existing_item.setItem_name(updated_item.getItem_name());
//            }
//
//            if  (updated_item.getImage_path() != null){
//                existing_item.setImage_path(updated_item.getImage_path());
//            }
//
//            if (updated_item.getItem_description() != null){
//                existing_item.setItem_description(updated_item.getItem_description());
//            }
//
//            if (updated_item.getItem_price() != 0){
//                existing_item.setItem_price(updated_item.getItem_price());
//            }
//
//            return item_repo.save(existing_item);
//        }
//
//        return null;
//    }
//
//    public void deleteItem(int item_id) {
//        item_repo.deleteById(item_id);
//    }
//

}
