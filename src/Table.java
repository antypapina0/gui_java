public class Table {
    private Object[][] table_data = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},};

    public Object[][] getTable_data() {
        return table_data;
    }

    public void setValue(int row, int column, int value) {
        table_data[row][column] = value;
    }

    public int getValue(int row, int column) {
        int temp = (int) table_data[row][column];
        return temp;
    }

    public void resetValues() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                setValue(i, j, 0);
            }
        }
    }

    public int AddTableElements()
    {
        int result = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                result = result + getValue(i,j);
            }
        }
        return result;
    }

    public double AverageTableElements()
    {
        double sum = AddTableElements();

        return sum / 25.0;
    }

    public int MaxTableElement() {
        int result = 0;
        result = getValue(0, 0);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (getValue(i, j) >= result)
                    result = getValue(i, j);
            }
        }
        return result;
    }

    public int MinTableElement() {
        int result = 0;
        result = getValue(0, 0);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (getValue(i, j) <= result)
                    result = getValue(i, j);
            }
        }
        return result;
    }

}
