package com.mypackage.lab3_5;

/**
 * Class for generic conversion
 */
public interface TypeChangeFunction<IN, OUT> {
    /**
     * Conversion function
     *
     * @param value value to be converted
     * @return converted value into specified type
     */
    OUT convert(IN value);
}
