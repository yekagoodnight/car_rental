<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'"  label="租赁标题" prop="zulinbiaoti" >
					<el-input v-model="ruleForm.zulinbiaoti" placeholder="租赁标题" clearable  :readonly="ro.zulinbiaoti"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="租赁标题" prop="zulinbiaoti" >
					<el-input v-model="ruleForm.zulinbiaoti" placeholder="租赁标题" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="汽车车牌" prop="qichechepai" >
					<el-input v-model="ruleForm.qichechepai" placeholder="汽车车牌" clearable  :readonly="ro.qichechepai"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="汽车车牌" prop="qichechepai" >
					<el-input v-model="ruleForm.qichechepai" placeholder="汽车车牌" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="汽车类型" prop="qicheleixing" >
					<el-select :disabled="ro.qicheleixing" v-model="ruleForm.qicheleixing" placeholder="请选择汽车类型" >
						<el-option
							v-for="(item,index) in qicheleixingOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="汽车类型" prop="qicheleixing" >
					<el-input v-model="ruleForm.qicheleixing"
						placeholder="汽车类型" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="地区" prop="diqu" >
					<el-input v-model="ruleForm.diqu" placeholder="地区" clearable  :readonly="ro.diqu"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="地区" prop="diqu" >
					<el-input v-model="ruleForm.diqu" placeholder="地区" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="品牌" prop="pinpai" >
					<el-input v-model="ruleForm.pinpai" placeholder="品牌" clearable  :readonly="ro.pinpai"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="品牌" prop="pinpai" >
					<el-input v-model="ruleForm.pinpai" placeholder="品牌" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="型号" prop="xinghao" >
					<el-input v-model="ruleForm.xinghao" placeholder="型号" clearable  :readonly="ro.xinghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="型号" prop="xinghao" >
					<el-input v-model="ruleForm.xinghao" placeholder="型号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="年份" prop="nianfen" >
					<el-input v-model="ruleForm.nianfen" placeholder="年份" clearable  :readonly="ro.nianfen"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="年份" prop="nianfen" >
					<el-input v-model="ruleForm.nianfen" placeholder="年份" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="状态" prop="zhuangtai" >
					<el-select :disabled="ro.zhuangtai" v-model="ruleForm.zhuangtai" placeholder="请选择状态" >
						<el-option
							v-for="(item,index) in zhuangtaiOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="状态" prop="zhuangtai" >
					<el-input v-model="ruleForm.zhuangtai"
						placeholder="状态" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="日租金" prop="rizujin" >
					<el-input-number v-model="ruleForm.rizujin" placeholder="日租金" :disabled="ro.rizujin"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="日租金" prop="rizujin" >
					<el-input v-model="ruleForm.rizujin" placeholder="日租金" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info'&& !ro.jiancebaogao" label="检测报告" prop="jiancebaogao" >
					<file-upload
						tip="点击上传检测报告"
						action="file/upload"
						:limit="1"
						:type="3"
						:multiple="true"
						:fileUrls="ruleForm.jiancebaogao?ruleForm.jiancebaogao:''"
						@change="jiancebaogaoUploadChange"
					></file-upload>
				</el-form-item>  
				<el-form-item v-else-if="ruleForm.jiancebaogao" label="检测报告" prop="jiancebaogao" >
					<el-button class="downBtn" type="text" size="small" @click="download($base.url+ruleForm.jiancebaogao)">
						<span class="icon iconfont icon-xiazai6"></span>
						下载
					</el-button>
				</el-form-item>
				<el-form-item v-else-if="!ruleForm.jiancebaogao" label="检测报告" prop="jiancebaogao" >
					<el-button class="unBtn" type="text" size="small">
						<span class="icon iconfont icon-xihuan"></span>
						暂无
					</el-button>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.qichetupian" label="汽车图片" prop="qichetupian" >
					<file-upload
						tip="点击上传汽车图片"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.qichetupian?ruleForm.qichetupian:''"
						@change="qichetupianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.qichetupian" label="汽车图片" prop="qichetupian" >
					<img v-if="ruleForm.qichetupian.substring(0,4)=='http'&&ruleForm.qichetupian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.qichetupian" width="100" height="100">
					<img v-else-if="ruleForm.qichetupian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.qichetupian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.qichetupian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="车商账号" prop="cheshangzhanghao" >
					<el-input v-model="ruleForm.cheshangzhanghao" placeholder="车商账号" clearable  :readonly="ro.cheshangzhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="车商账号" prop="cheshangzhanghao" >
					<el-input v-model="ruleForm.cheshangzhanghao" placeholder="车商账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="车商姓名" prop="cheshangxingming" >
					<el-input v-model="ruleForm.cheshangxingming" placeholder="车商姓名" clearable  :readonly="ro.cheshangxingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="车商姓名" prop="cheshangxingming" >
					<el-input v-model="ruleForm.cheshangxingming" placeholder="车商姓名" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item v-if="type!='info'"  label="汽车配置" prop="qichepeizhi" >
				<editor 
					style="min-width: 200px; max-width: 600px;"
					v-model="ruleForm.qichepeizhi" 
					class="editor" 
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.qichepeizhi" label="汽车配置" prop="qichepeizhi" >
				<span class="text ql-snow ql-editor" v-html="ruleForm.qichepeizhi"></span>
			</el-form-item>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-xihuan"></span>
					确定
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

	</div>
</template>
<script>
	import { 
		isNumber,
		isIntNumer,
	} from "@/utils/validate";
	export default {
		data() {
			var validateNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isNumber(value)) {
					callback(new Error("请输入数字"));
				} else {
					callback();
				}
			};
			var validateIntNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isIntNumer(value)) {
					callback(new Error("请输入整数"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
				ro:{
					zulinbiaoti : false,
					qichechepai : false,
					qicheleixing : false,
					diqu : false,
					pinpai : false,
					xinghao : false,
					nianfen : false,
					zhuangtai : false,
					rizujin : false,
					jiancebaogao : false,
					qichepeizhi : false,
					qichetupian : false,
					cheshangzhanghao : false,
					cheshangxingming : false,
					clicktime : false,
					clicknum : false,
					discussnum : false,
					userid : false,
					storeupnum : false,
				},
			
				ruleForm: {
					zulinbiaoti: '',
					qichechepai: '',
					qicheleixing: '',
					diqu: '',
					pinpai: '',
					xinghao: '',
					nianfen: '',
					zhuangtai: '待租赁',
					rizujin: '',
					jiancebaogao: '',
					qichepeizhi: '',
					qichetupian: '',
					cheshangzhanghao: '',
					cheshangxingming: '',
					clicktime: '',
					userid: '',
				},
				qicheleixingOptions: [],
				zhuangtaiOptions: [],

				rules: {
					zulinbiaoti: [
						{ required: true, message: '租赁标题不能为空', trigger: 'blur' },
					],
					qichechepai: [
						{ required: true, message: '汽车车牌不能为空', trigger: 'blur' },
					],
					qicheleixing: [
						{ required: true, message: '汽车类型不能为空', trigger: 'blur' },
					],
					diqu: [
						{ required: true, message: '地区不能为空', trigger: 'blur' },
					],
					pinpai: [
						{ required: true, message: '品牌不能为空', trigger: 'blur' },
					],
					xinghao: [
						{ required: true, message: '型号不能为空', trigger: 'blur' },
					],
					nianfen: [
						{ required: true, message: '年份不能为空', trigger: 'blur' },
					],
					zhuangtai: [
						{ required: true, message: '状态不能为空', trigger: 'blur' },
					],
					rizujin: [
						{ required: true, message: '日租金不能为空', trigger: 'blur' },
						{ validator: validateNumber, trigger: 'blur' },
					],
					jiancebaogao: [
						{ required: true, message: '检测报告不能为空', trigger: 'blur' },
					],
					qichepeizhi: [
					],
					qichetupian: [
					],
					cheshangzhanghao: [
					],
					cheshangxingming: [
					],
					clicktime: [
					],
					clicknum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					discussnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					userid: [
					],
					storeupnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
				},
			};
		},
		props: ["parent"],
		computed: {



		},
		components: {
		},
		created() {
		},
		methods: {
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o=='zulinbiaoti'){
							this.ruleForm.zulinbiaoti = obj[o];
							this.ro.zulinbiaoti = true;
							continue;
						}
						if(o=='qichechepai'){
							this.ruleForm.qichechepai = obj[o];
							this.ro.qichechepai = true;
							continue;
						}
						if(o=='qicheleixing'){
							this.ruleForm.qicheleixing = obj[o];
							this.ro.qicheleixing = true;
							continue;
						}
						if(o=='diqu'){
							this.ruleForm.diqu = obj[o];
							this.ro.diqu = true;
							continue;
						}
						if(o=='pinpai'){
							this.ruleForm.pinpai = obj[o];
							this.ro.pinpai = true;
							continue;
						}
						if(o=='xinghao'){
							this.ruleForm.xinghao = obj[o];
							this.ro.xinghao = true;
							continue;
						}
						if(o=='nianfen'){
							this.ruleForm.nianfen = obj[o];
							this.ro.nianfen = true;
							continue;
						}
						if(o=='zhuangtai'){
							this.ruleForm.zhuangtai = obj[o];
							this.ro.zhuangtai = true;
							continue;
						}
						if(o=='rizujin'){
							this.ruleForm.rizujin = obj[o];
							this.ro.rizujin = true;
							continue;
						}
						if(o=='jiancebaogao'){
							this.ruleForm.jiancebaogao = obj[o];
							this.ro.jiancebaogao = true;
							continue;
						}
						if(o=='qichepeizhi'){
							this.ruleForm.qichepeizhi = obj[o];
							this.ro.qichepeizhi = true;
							continue;
						}
						if(o=='qichetupian'){
							this.ruleForm.qichetupian = obj[o];
							this.ro.qichetupian = true;
							continue;
						}
						if(o=='cheshangzhanghao'){
							this.ruleForm.cheshangzhanghao = obj[o];
							this.ro.cheshangzhanghao = true;
							continue;
						}
						if(o=='cheshangxingming'){
							this.ruleForm.cheshangxingming = obj[o];
							this.ro.cheshangxingming = true;
							continue;
						}
						if(o=='clicktime'){
							this.ruleForm.clicktime = obj[o];
							this.ro.clicktime = true;
							continue;
						}
						if(o=='clicknum'){
							this.ruleForm.clicknum = obj[o];
							this.ro.clicknum = true;
							continue;
						}
						if(o=='discussnum'){
							this.ruleForm.discussnum = obj[o];
							this.ro.discussnum = true;
							continue;
						}
						if(o=='userid'){
							this.ruleForm.userid = obj[o];
							this.ro.userid = true;
							continue;
						}
						if(o=='storeupnum'){
							this.ruleForm.storeupnum = obj[o];
							this.ro.storeupnum = true;
							continue;
						}
					}
					this.ruleForm.zhuangtai = '待租赁'; 				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(((json.cheshangzhanghao!=''&&json.cheshangzhanghao) || json.cheshangzhanghao==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.cheshangzhanghao = json.cheshangzhanghao
							this.ro.cheshangzhanghao = true;
						}
						if(((json.cheshangxingming!=''&&json.cheshangxingming) || json.cheshangxingming==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.cheshangxingming = json.cheshangxingming
							this.ro.cheshangxingming = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
				this.$http({
					url: `option/qicheleixing/qicheleixing`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.qicheleixingOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
				this.zhuangtaiOptions = "已租赁,待租赁".split(',')
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `qichechuzu/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.qichepeizhi = this.ruleForm.qichepeizhi.replace(reg,'../../../springboot0aqexa96/upload');
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.jiancebaogao!=null) {
						this.ruleForm.jiancebaogao = this.ruleForm.jiancebaogao.replace(new RegExp(this.$base.url,"g"),"");
					}
					if(this.ruleForm.qichetupian!=null) {
						this.ruleForm.qichetupian = this.ruleForm.qichetupian.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
									}
								}
							}
							
							await this.$http({
								url: `qichechuzu/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.qichechuzuCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
								} else {
									this.$message.error(data.msg);
								}
							});
						}
					});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.qichechuzuCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			jiancebaogaoUploadChange(fileUrls) {
				this.ruleForm.jiancebaogao = fileUrls;
			},
			qichetupianUploadChange(fileUrls) {
				this.ruleForm.qichetupian = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 30px;
	}
	.add-update-preview {
		padding: 40px 30px;
		background: none;
		border-color: #eee;
		border-width: 0px 0 0;
		border-style: solid;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		border: 0px solid #eee;
		padding: 0;
		margin: 0 0 26px 0;
		display: inline-block;
		width: 49%;
	}
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #6e6e6e;
		font-weight: 500;
		width: 180px;
		font-size: 15px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 180px;
	}
	.add-update-preview .el-form-item span.text {
		padding: 0 10px;
		color: #666;
		background: none;
		font-weight: 500;
		display: inline-block;
		font-size: 15px;
		line-height: 40px;
		min-width: 100%;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 15px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 15px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
	}
	.add-update-preview .el-select /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px;
		color: #666;
		width: 100%;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 10px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 34px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 0;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 0px;
		margin: 0 20px 0 0;
		outline: none;
		color: #999;
		background: none;
		width: auto;
		font-size: 15px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 34px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	.add-update-preview /deep/ .el-upload__tip {
		color: #666;
		font-size: 15px;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 12px;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		min-width: 100%;
		height: 120px;
	}
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
				border: 0;
				cursor: not-allowed;
				border-radius: 0px;
				padding: 12px;
				color: #666;
				background: none;
				width: auto;
				font-size: 15px;
				min-width: 400px;
				height: auto;
			}
	.add-update-preview .el-form-item.btn {
		padding: 0;
		margin: 20px 0 0;
		.btn1 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #1a3194;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #1fc3cb;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 34px;
			}
		}
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #32c67a;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #b3b3b3;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #156286;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn5:hover {
			opacity: 0.8;
		}
	}
</style>
