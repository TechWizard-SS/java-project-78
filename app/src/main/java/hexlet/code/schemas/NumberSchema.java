package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean positive = false;
    private Integer min = null;
    private Integer max = null;


    @Override
    public NumberSchema required() { // для сохранения типа, иначе возвр. BaseSchema<Integer>
        super.required();
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int minValue, int maxValue) {  // checkstyle ругается на похожие имена((
        if (minValue > maxValue) {
            throw new IllegalArgumentException("min should not be greater than max");
        }
        this.min = minValue;
        this.max = maxValue;
        return this;
    }

    @Override
    public boolean isValid(Integer value) {
        // null валиден только без required
        if (value == null && !required) {
            return true;
        }
        // null невалиден с required
        if (value == null) {
            return false;
        }

        if (positive && value <= 0) {
            return false;
        }
        if (this.min != null && value < this.min) {
            return false;
        }
        if (this.max != null && value > this.max) {
            return false;
        }
        return true;
    }
}
