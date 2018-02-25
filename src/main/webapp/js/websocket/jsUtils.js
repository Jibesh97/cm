/* 
 *  方法:Array.remove(dx) 
 *  功能:根据元素位置值删除数组元素. 
 *  参数:元素值 
 *  返回:在原数组上修改数组 
 *  作者：liy 
 */  
Array.prototype.remove = function (dx) {  
    if (isNaN(dx) || dx > this.length) {  
        return false;  
    }  
    for (var i = 0, n = 0; i < this.length; i++) {  
        if (this[i] != this[dx]) {  
            this[n++] = this[i];  
        }  
    }  
    this.length -= 1;  
};  

Array.prototype.removevalue = function (val) {  
    var index = this.indexOf(val);  
    if (index > -1) {  
        this.splice(index, 1);  
    }  
};    

Array.prototype.removeItem = function(val) {  
  var index = this.indexOf(val);  
  if (index > -1) {  
      this.splice(index, 1);  
  }  
}; 