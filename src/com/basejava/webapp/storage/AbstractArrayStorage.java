package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void delete(String uuid) {
        int index = getResumeIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме с uuid: " + uuid + " - не найдено!");
        } else {
            deleteResume(index);
            storage[size - 1] = null;
            size--;
        }
    }

    protected abstract void deleteResume(int index);

    public Resume get(String uuid) {
        int index = getResumeIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме с uuid: " + uuid + " - не найдено!");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getResumeIndex(String uuid);

    public void save(Resume resume) {
        int index = getResumeIndex(resume.getUuid());
        if (index >= 0) {
            System.out.println("Резюме с uuid: " + resume.getUuid() + " - уже есть в хранилище!");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Хранилище переполнено, нужно освободить место!");
        } else {
            saveResume(index, resume);
            size++;
        }
    }

    protected abstract void saveResume(int index, Resume resume);

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int index = getResumeIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Резюме с uuid: " + resume.getUuid() + " - не найдено!");
        } else {
            storage[index] = resume;
        }
    }
}
