package com.springys.Common;

import lombok.Data;

@Data
public class TokenResult {
  private   String [] roles;
  private  String name;
  private  String introduction;
  private  String avatar;
}
