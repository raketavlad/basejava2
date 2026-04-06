package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void deleteResume(int index) {
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
    }

    @Override
    protected int getResumeIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    protected void saveResume(int index, Resume resume) {
        int indexNewResume = -(index + 1);
        for (int i = size; i > indexNewResume; i--) {
            storage[i] = storage[i - 1];
        }
        storage[indexNewResume] = resume;
    }
}
