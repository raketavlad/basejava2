package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getResumeIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Резюме с uuid: " + resume.getUuid() + " - не найдено!");
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        if (size >= storage.length) {
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

    public Resume get(String uuid) {
        int index = getResumeIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме с uuid: " + uuid + " - не найдено!");
            return null;
        }
        return storage[index];
    }

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

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
