/**
 * 表格导出函数（依赖于layui）
 * 使用：
 *  - 将excel.js放入layuiadmin目录下。
 *  - layui.use(['excel'],function(){ var excel = layui.excel;})。
 *  - data类型为一维数组[{},{},...]
 *  - cols类型为二维数组，符合layui数据表格的cols数据格式。
 *  - fileName为标题和文件名
 *  - newStr扩展行数据
 */
function exportFile(excel,data,cols,fileName,newStr) {
	var chars = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
	var bodysArr = new Array(); // excel导出的数据格式
	var rows = new Array(); // 列名集合
	var fields = new Array();// 列字段
	var sumFields = new Array(); // 需要合计的字段
	var merges = new Array(); // 合并单元格的数据格式
	var offsets = new Array(); // 偏移值集合
	var fileNames = {'0':fileName}; // 标题头
	var sums = {'0':'合计'};
	var newRow = {};// 扩展行
	for(var i=0;i<cols.length;i++){// 遍历标题行
		var colNames = {};
		var colNamesLen = 0;
		offsets[i] = new Array();
		for(var j=0;j<cols[i].length;j++){// 遍历标题列
			offsets[i][colNamesLen] = offsets[i][colNamesLen-1]?offsets[i][colNamesLen-1]:0; // 获取上一偏移值
			if(cols[i][j].toolbar || !cols[i][j].title) // 忽略工具列、无效列（例如：radio）
				continue;
			//console.log(i+":"+j)
			// 1、是否被跨行，补空字符
			while(isRowspan(cols,i,colNamesLen,1,rows,offsets)){
				colNames[colNamesLen + ''] = ''; // 被跨行，补空字符
				offsets[i][colNamesLen] = offsets[i][colNamesLen]?offsets[i][colNamesLen]+1:1;// 累加偏移值
				colNamesLen++;
			}
			var obj = cols[i][j];
			// 2、是否跨行，记录位置
			if(obj.rowspan){
				var index = parseInt(obj.rowspan)-1; // -1除当前行
				var start = chars[colNamesLen] + (i+1+1); // 索引从0开始，所以+1，有标题头，所以再+1
				var end = chars[colNamesLen] + (i+1+1+index);// 同上
				merges.push([start,end]);
			}
			// 3、添加当前索引的列
			//console.log(obj.title);
			colNames[colNamesLen + ''] = obj.title;
			colNamesLen++;
			
			// 4、是否跨列，补空字符，记录位置
			if(obj.colspan){
				var start = chars[colNamesLen-1] + (i+1+1); // -1:表示除本身，索引从0开始，所以+1，有标题头，所以再+1
				// 给右边补空格
				for(var x = 0;x<parseInt(obj.colspan)-1;x++){ // -1,表示除本身
					colNames[colNamesLen + ''] = '';
					offsets[i][colNamesLen] = offsets[i][colNamesLen]?offsets[i][colNamesLen]+1:1;// 累加偏移值
					colNamesLen++;
				}
				var end = chars[colNamesLen-1] + (i+1+1); // -1:表示除本身， 索引从0开始，所以+1，有标题头，所以再+1
				merges.push([start,end]);
			}
			
			// 5、记录字段
			if(obj.field){
				fields[colNamesLen-1] = obj.field;
				if(obj.totalRow)
					sumFields.push(obj.field); // 记录合计字段
			}else if(!obj.title){
				fields[colNamesLen-1] = ''; // 空字符占位
			}
		}
		rows.push(colNames);
	}
	// 给扩展行添加数据
	if(newStr)
		newRow['0'] = newStr;
	for (var i in data) {//遍历行
		var line = {};
		for (var j=0;j<fields.length;j++) {//遍历列
			// 给标题补空白单元格
			if(i==0)
				fileNames['' + j!=0?j:1] = '';
			if(i==0 && newRow['0'])
				newRow['' + j!=0?j:1] = '';
			// 存行的列数据
			if(fields[j] == ''){
				line['' + j] = '';
			}else{
				line['' + j] = data[i][fields[j]];
			}
			// 合计
			if(sumFields.length>0 && sumFields.indexOf(fields[j]) != -1){
				sums['' + j] = add(sums['' + j],data[i][fields[j]]);
			}
		}
		bodysArr.push(line);// 添加行
	}
	if(sumFields.length>0)
		bodysArr.push(sums); // 添加合计行
	if(newRow['0'])
		bodysArr.push(newRow); // 添加合计行
	bodysArr.unshift(...rows); // 添加表头
	bodysArr.unshift(fileNames);// 添加标题

	var lastCol = chars[fields.length - 1] + bodysArr.length;
	
	//设置列标题样式
	excel.setExportCellStyle(bodysArr, 'A1:'+lastCol, {
		s: {
			fill: {fgColor: { rgb: "DDDDDD" } },
			font: {bold: true}
		}
	},function(cell, newCell, row, config, currentRow, currentCol, fieldKey) {
		// 回调参数，cell:原有数据，newCell:根据批量设置规则自动生成的样式，row:所在行数据，config:传入的配置,currentRow:当前行索引,currentCol:当前列索引，fieldKey:当前字段索引
		return (currentRow <= rows.length) ? newCell : cell;
	});
	
	// 设置合计行样式
	if((sumFields.length>0 && !newRow[0]) || (sumFields.length<=0 && newRow[0]))
		excel.setExportCellStyle(bodysArr, 'A'+(rows.length+1+1)+':'+lastCol, { // 表头+标题+1起始
			s: {
				fill: {fgColor: { rgb: "DDDDDD" } }
			}
		}, function(cell, newCell, row, config, currentRow, currentCol, fieldKey) {
			return (currentRow != bodysArr.length - 1) ? cell : newCell ;// 合计上色
		});
	else
		excel.setExportCellStyle(bodysArr, 'A'+(rows.length+1+1)+':'+lastCol, { // 表头+标题+1起始
			s: {
				fill: {fgColor: { rgb: "DDDDDD" } }
			}
		}, function(cell, newCell, row, config, currentRow, currentCol, fieldKey) {
			return (currentRow <= bodysArr.length - 3) ? cell : newCell ;// 合计上色
		});
	// 设置边框、文本居中
	excel.setExportCellStyle(bodysArr, 'A1:'+lastCol, {
		s: {
			alignment: {
				horizontal: 'center',
				vertical: 'center'
			},
			border: {
				top: {style: 'thin', color: 'FFFFFF'},
				bottom: {style: 'thin', color: 'FFFFFF'},
				left: {style: 'thin', color: 'FFFFFF'},
				right: {style: 'thin', color: 'FFFFFF'}
			}
		}
	});
	
	// 行高
	var height = {}
	height[bodysArr.length] = 30; // 最后一行30px的同时，之前的则为默认值，否则未设值。(索引从1开始)
	var rowConf = excel.makeRowConfig(height, 30);//设置默认值为30
	
	// 列宽
	var width = {}
	width[chars[fields.length - 1]] = 110;
	var colConf = excel.makeColConfig(width, 110);

	// 合并单元格
	merges.push(['A1', chars[fields.length - 1] + 1]); // 标题合并
	// 扩展行
	if(newRow['0'])
		merges.push(['A'+bodysArr.length, chars[fields.length - 1] + bodysArr.length]);
	var mergeConf = excel.makeMergeConfig(merges);

	//console.log(cols)
	//console.log(rows);
	//console.log(fields);
	//console.log(merges);
	//导出excel
	excel.exportExcel({
		sheet1: bodysArr
	}, fileName + '.xlsx', 'xlsx', {
		extend: {
			'!rows': rowConf,// 行高
			'!cols': colConf,// 列宽
			'!merges': mergeConf //合并单元格
		}
	});
}


/**
 * 判断是否跨行
 * @param {二维数组} cols 符合layui数据表格的cols数据
 * @param {整数} i 行索引
 * @param {整数} j 列索引
 * @param {整数} h 迭代深度
 * @param {整数} rows 记录的标题行
 * @param {整数} offsets 偏移值集合
 * @return {boolean} 是否跨行
 * 例如：a/b/c/d 四行，其中a跨3行
 * a  4 3 2
 * b  3 2 1->深度2，小于3，跨行
 * c  2 1--->深度3，等于3，跨行
 * d  1 ---->深度4，大于3，不跨
 */
function isRowspan(cols,i,j,h,rows,offsets){
	if(!cols[--i])return false; // 上一行为空
	
	var preObj = cols[i][j];// 获取上一行的列
	if(i==0 && preObj && preObj.toolbar) return false; // 第一行，当前列不为空，并且是工具列，则忽略
	
	if(!preObj || !preObj.rowspan){// 为空
		return isRowspan(cols,i,j,++h,rows,offsets); // 递归往上判断是否跨行
	}else if(h <= preObj.rowspan){// 找到了,判断其迭代深度是否小于等于要跨的行数。
		var offset = offsets[i]&&offsets[i][j]?offsets[i][j]:0;
		return cols[i][j-offset].title == rows[i][j+'']; // 则再判断 列title与记录title不一致，说明列title位置发生变化
	}else{ // 否则不跨
		return false;
	}
}

/**
 * 求两数之和
 * @param {String} str1 字符串类型数字1
 * @param {Object} str2 字符串类型数字2
 */
function add(str1,str2){
	// 非空判断
	str1 = str1?str1+'':'0';
	str2 = str2?str2+'':'0';

	// 转整数运算
	var a = parseFloat(str1,2);
	var b = parseFloat(str2,2);
	return ae_jia(a,b);
}

/**
 * 打印该对象的所有属性到控制台
 * @param {Object} obj 对象
 */
function ShowTheObject(obj){
  var des = "";
  for(var name in obj){
	des += name + ":" + obj[name] + ";";
  }
  console.log(des)
}
/**
2  ** 加法函数，用来得到精确的加法结果
3  ** 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
4  ** 调用：accAdd(arg1,arg2)
5  ** 返回值：arg1加上arg2的精确结果
6  **/
function ae_jia(arg1, arg2) {
    var r1, r2, m, c;
    try {
       r1 = arg1.toString().split(".")[1].length;
   }
   catch (e) {
       r1 = 0;
   }
   try {
       r2 = arg2.toString().split(".")[1].length;
   }
   catch (e) {
       r2 = 0;
   }
   c = Math.abs(r1 - r2);
   m = Math.pow(10, Math.max(r1, r2));
   if (c > 0) {
       var cm = Math.pow(10, c);
       if (r1 > r2) {
           arg1 = Number(arg1.toString().replace(".", ""));
           arg2 = Number(arg2.toString().replace(".", "")) * cm;
       } else {
           arg1 = Number(arg1.toString().replace(".", "")) * cm;
           arg2 = Number(arg2.toString().replace(".", ""));
       }
   } else {
       arg1 = Number(arg1.toString().replace(".", ""));
       arg2 = Number(arg2.toString().replace(".", ""));
   }
   return (arg1 + arg2) / m;
}