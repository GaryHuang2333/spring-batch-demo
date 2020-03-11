package com.example.batch.common.misc;

import com.example.batch.common.entities.Staff;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.stereotype.Component;

@Component("evenOddStaffClassifier")
public class EvenOddStaffClassifier implements BinaryClassifier<Staff, ItemStreamWriter> {
    private ItemStreamWriter oddWriter;
    private ItemStreamWriter evenWriter;

    /**
     * @param oddWriter  奇数
     * @param evenWriter 偶数
     */
    @Override
    public void setBinaryObject(ItemStreamWriter oddWriter, ItemStreamWriter evenWriter) {
        this.oddWriter = oddWriter;
        this.evenWriter = evenWriter;
    }

    @Override
    public ItemStreamWriter classify(Staff staff) {
        ItemStreamWriter writer = staff.getId() % 2 == 0 ? evenWriter : oddWriter;
        return writer;
    }
}
