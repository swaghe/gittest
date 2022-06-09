package com.hbb.mycat.test.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author hjc
 * @since 2022-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String c2;


}
