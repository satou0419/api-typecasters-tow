package com.typecasters.apitowerofwords.Controller;

import com.typecasters.apitowerofwords.Entity.ItemEntity;
import com.typecasters.apitowerofwords.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService item_serv;

    public ItemController(ItemService item_serv) {
        this.item_serv = item_serv;
    }

//    @PostMapping("/add_item")
//    public ResponseEntity<ItemEntity> addItem(@RequestBody ItemEntity item){
//        ItemEntity saved_item = item_serv.addItemWithUserItems(item);
//        return  ResponseEntity.ok(saved_item);
//    }

    @GetMapping("/get_all_items")
    public ResponseEntity<List<ItemEntity>> getAllItems() {
        List<ItemEntity> all_items = item_serv.getAllItems();
        return ResponseEntity.ok(all_items);
    }


    @GetMapping("/get_item_by_id/{item_id}")
    public ResponseEntity<ItemEntity> getItemById(@PathVariable int item_id){
        ItemEntity item = item_serv.getItembyId(item_id);

        if(item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update_item/{item_id}")
    public ResponseEntity<ItemEntity> updateItem(@PathVariable int item_id, @RequestBody ItemEntity updated_item){
        ItemEntity updated_item_entity = item_serv.updateItem(item_id, updated_item);

        if  (updated_item_entity != null){
            return  ResponseEntity.ok(updated_item_entity);
        }   else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete_item/{item_id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int item_id){
        item_serv.deleteItem(item_id);
        return ResponseEntity.noContent().build();
    }

}