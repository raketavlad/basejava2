package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    public void delete(String uuid) {
        int index = getResumeIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме с uuid: " + uuid + " - не найдено!");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Хранилище переполнено, нужно освободить место!");
        } else {
            if (getResumeIndex(resume.getUuid()) >= 0) {
                System.out.println("Резюме с uuid: " + resume.getUuid() + " - уже есть в хранилище!");
            } else {
                storage[size] = resume;
                size++;
            }
        }
    }

    protected int getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
