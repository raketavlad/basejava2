import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume resume) {
        int index = getResumeIndex(resume.uuid);
        if (index >= 0) {
            System.out.println("Резюме с таким uuid уже есть в хранилище!");
        } else {
            storage[size()] = resume;
        }
    }

    Resume get(String uuid) {
        int index = getResumeIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме с таким uuid не найдено!");
            return null;
        } else {
            return storage[index];
        }
    }

    void delete(String uuid) {
        int index = getResumeIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме с таким uuid не найдено!");
        } else {
            int size = size();
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
        }
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int countResume = 0;
        while (storage[countResume] != null) {
            countResume++;
        }
        return countResume;
    }

    private int getResumeIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (storage[i].toString().equals(uuid)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
