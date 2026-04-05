package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void delete(String uuid) {
        int index = getResumeIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме с uuid: " + uuid + " - не найдено!");
        } else {
            for (int i = index; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Хранилище переполнено, нужно освободить место!");
        } else {
            int index = getResumeIndex(resume.getUuid());
            if (index >= 0) {
                System.out.println("Резюме с uuid: " + resume.getUuid() + " - уже есть в хранилище!");
            } else {
                int indexNewResume = -(index + 1);
                for (int i = size; i > indexNewResume; i--) {
                    storage[i] = storage[i - 1];
                }
                storage[indexNewResume] = resume;
                size++;
            }
        }
    }

    @Override
    protected int getResumeIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
