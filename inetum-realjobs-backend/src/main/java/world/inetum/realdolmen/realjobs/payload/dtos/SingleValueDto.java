package world.inetum.realdolmen.realjobs.payload.dtos;

public class SingleValueDto<T> {

    private T value;

    public SingleValueDto() {
    }

    public SingleValueDto(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
