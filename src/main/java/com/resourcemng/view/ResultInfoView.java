package com.resourcemng.view;

import com.resourcemng.Enum.IsDelete;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017-6-23.
 */
public class ResultInfoView {
  private BigDecimal total;
  private BigDecimal materialMake;
  private BigDecimal companyCase;
  private BigDecimal courseDevelopment;
  private BigDecimal specialTool;
  private BigDecimal applicationPromete;
  private BigDecimal researchProve;
  private BigDecimal expertConsult;
  private BigDecimal otherFee;

}
