package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    protected void fillDeleteElement(int index) {
        storage[index] = storage[size - 1];
    }

    protected int getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected void insertElement(int index, Resume resume) {
        storage[size] = resume;
    }
}
