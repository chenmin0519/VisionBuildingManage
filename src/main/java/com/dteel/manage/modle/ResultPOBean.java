package com.dteel.manage.modle;

 public class ResultPOBean<T> extends ResultBean
 {
   private static final long serialVersionUID = 2786819177462037719L;
   private T value;
 
   public T getValue()
   {
        return this.value;
   }
 
   public void setValue(T value)
   {
        this.value = value;
   }
 
   public void success(T value)
   {
        setStatus(Result.success.toString());
        this.value = value;
   }
   
   public boolean isEmpty() {
       return this.value==null ? true :false ;
   }
 }

 