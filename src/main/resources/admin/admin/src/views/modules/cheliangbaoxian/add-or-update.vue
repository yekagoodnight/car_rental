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
				<el-form-item class="input" v-if="type!='info'" label="保单编号" prop="baodanbianhao" >
					<el-input v-model="ruleForm.baodanbianhao" placeholder="保单编号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.baodanbianhao" label="保单编号" prop="baodanbianhao" >
					<el-input v-model="ruleForm.baodanbianhao" placeholder="保单编号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="汽车车牌" prop="qichechepai" >
					<el-input v-model="ruleForm.qichechepai" placeholder="汽车车牌" clearable  :readonly="ro.qichechepai"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="汽车车牌" prop="qichechepai" >
					<el-input v-model="ruleForm.qichechepai" placeholder="汽车车牌" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="汽车类型" prop="qicheleixing" >
					<el-input v-model="ruleForm.qicheleixing" placeholder="汽车类型" clearable  :readonly="ro.qicheleixing"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="汽车类型" prop="qicheleixing" >
					<el-input v-model="ruleForm.qicheleixing" placeholder="汽车类型" readonly></el-input>
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
				<el-form-item class="input" v-if="type!='info'"  label="保险名称" prop="baoxianmingcheng" >
					<el-input v-model="ruleForm.baoxianmingcheng" placeholder="保险名称" clearable  :readonly="ro.baoxianmingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="保险名称" prop="baoxianmingcheng" >
					<el-input v-model="ruleForm.baoxianmingcheng" placeholder="保险名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="保险类型" prop="baoxianleixing" >
					<el-select :disabled="ro.baoxianleixing" v-model="ruleForm.baoxianleixing" placeholder="请选择保险类型" >
						<el-option
							v-for="(item,index) in baoxianleixingOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="保险类型" prop="baoxianleixing" >
					<el-input v-model="ruleForm.baoxianleixing"
						placeholder="保险类型" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="办理时间" prop="banlishijian" >
					<el-date-picker
						value-format="yyyy-MM-dd HH:mm:ss"
						v-model="ruleForm.banlishijian" 
						type="datetime"
						:readonly="ro.banlishijian"
						placeholder="办理时间"
					></el-date-picker>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.banlishijian" label="办理时间" prop="banlishijian" >
					<el-input v-model="ruleForm.banlishijian" placeholder="办理时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="到期时间" prop="daoqishijian" >
					<el-date-picker
						value-format="yyyy-MM-dd HH:mm:ss"
						v-model="ruleForm.daoqishijian" 
						type="datetime"
						:readonly="ro.daoqishijian"
						:picker-options="daoqishijianPickerOptions"
						placeholder="到期时间"
					></el-date-picker>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.daoqishijian" label="到期时间" prop="daoqishijian" >
					<el-input v-model="ruleForm.daoqishijian" placeholder="到期时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="保险费用" prop="baoxianfeiyong" >
					<el-input v-model.number="ruleForm.baoxianfeiyong" placeholder="保险费用" clearable  :readonly="ro.baoxianfeiyong"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="保险费用" prop="baoxianfeiyong" >
					<el-input v-model="ruleForm.baoxianfeiyong" placeholder="保险费用" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info'&& !ro.baoxianfujian" label="保险附件" prop="baoxianfujian" >
					<file-upload
						tip="点击上传保险附件"
						action="file/upload"
						:limit="1"
						:type="3"
						:multiple="true"
						:fileUrls="ruleForm.baoxianfujian?ruleForm.baoxianfujian:''"
						@change="baoxianfujianUploadChange"
					></file-upload>
				</el-form-item>  
				<el-form-item v-else-if="ruleForm.baoxianfujian" label="保险附件" prop="baoxianfujian" >
					<el-button class="downBtn" type="text" size="small" @click="download($base.url+ruleForm.baoxianfujian)">
						<span class="icon iconfont icon-xiazai6"></span>
						下载
					</el-button>
				</el-form-item>
				<el-form-item v-else-if="!ruleForm.baoxianfujian" label="保险附件" prop="baoxianfujian" >
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
				<el-form-item class="input" v-if="type!='info'"  label="用户账号" prop="yonghuzhanghao" >
					<el-input v-model="ruleForm.yonghuzhanghao" placeholder="用户账号" clearable  :readonly="ro.yonghuzhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="用户账号" prop="yonghuzhanghao" >
					<el-input v-model="ruleForm.yonghuzhanghao" placeholder="用户账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="用户姓名" prop="yonghuxingming" >
					<el-input v-model="ruleForm.yonghuxingming" placeholder="用户姓名" clearable  :readonly="ro.yonghuxingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="用户姓名" prop="yonghuxingming" >
					<el-input v-model="ruleForm.yonghuxingming" placeholder="用户姓名" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="车商账号" prop="cheshangzhanghao" >
					<el-input v-model="ruleForm.cheshangzhanghao" placeholder="车商账号" clearable  :readonly="ro.cheshangzhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="车商账号" prop="cheshangzhanghao" >
					<el-input v-model="ruleForm.cheshangzhanghao" placeholder="车商账号" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item class="textarea" v-if="type!='info'" label="保险描述" prop="baoxianmiaoshu" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="保险描述"
					v-model="ruleForm.baoxianmiaoshu" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.baoxianmiaoshu" label="保险描述" prop="baoxianmiaoshu" >
				<span class="text">{{ruleForm.baoxianmiaoshu}}</span>
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
		isIntNumer,
	} from "@/utils/validate";
	export default {
		data() {
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
					baodanbianhao : false,
					qichechepai : false,
					qicheleixing : false,
					pinpai : false,
					xinghao : false,
					baoxianmingcheng : false,
					baoxianleixing : false,
					banlishijian : false,
					daoqishijian : false,
					baoxianmiaoshu : false,
					baoxianfeiyong : false,
					baoxianfujian : false,
					qichetupian : false,
					yonghuzhanghao : false,
					yonghuxingming : false,
					cheshangzhanghao : false,
					ispay : false,
				},
			
				ruleForm: {
					baodanbianhao: this.getUUID(),
					qichechepai: '',
					qicheleixing: '',
					pinpai: '',
					xinghao: '',
					baoxianmingcheng: '',
					baoxianleixing: '',
					banlishijian: '',
					daoqishijian: '',
					baoxianmiaoshu: '',
					baoxianfeiyong: '',
					baoxianfujian: '',
					qichetupian: '',
					yonghuzhanghao: '',
					yonghuxingming: '',
					cheshangzhanghao: '',
				},
				baoxianleixingOptions: [],

				daoqishijianPickerOptions: {
					disabledDate(time) {
						return time.getTime() < Date.now() - 8.64e7;
					}
				},
				rules: {
					baodanbianhao: [
					],
					qichechepai: [
					],
					qicheleixing: [
					],
					pinpai: [
					],
					xinghao: [
					],
					baoxianmingcheng: [
						{ required: true, message: '保险名称不能为空', trigger: 'blur' },
					],
					baoxianleixing: [
						{ required: true, message: '保险类型不能为空', trigger: 'blur' },
					],
					banlishijian: [
					],
					daoqishijian: [
						{ required: true, message: '到期时间不能为空', trigger: 'blur' },
					],
					baoxianmiaoshu: [
						{ required: true, message: '保险描述不能为空', trigger: 'blur' },
					],
					baoxianfeiyong: [
						{ required: true, message: '保险费用不能为空', trigger: 'blur' },
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					baoxianfujian: [
						{ required: true, message: '保险附件不能为空', trigger: 'blur' },
					],
					qichetupian: [
					],
					yonghuzhanghao: [
					],
					yonghuxingming: [
					],
					cheshangzhanghao: [
					],
					ispay: [
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
			this.ruleForm.banlishijian = this.getCurDateTime()
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
						if(o=='baodanbianhao'){
							this.ruleForm.baodanbianhao = obj[o];
							this.ro.baodanbianhao = true;
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
						if(o=='baoxianmingcheng'){
							this.ruleForm.baoxianmingcheng = obj[o];
							this.ro.baoxianmingcheng = true;
							continue;
						}
						if(o=='baoxianleixing'){
							this.ruleForm.baoxianleixing = obj[o];
							this.ro.baoxianleixing = true;
							continue;
						}
						if(o=='banlishijian'){
							this.ruleForm.banlishijian = obj[o];
							this.ro.banlishijian = true;
							continue;
						}
						if(o=='daoqishijian'){
							this.ruleForm.daoqishijian = obj[o];
							this.ro.daoqishijian = true;
							continue;
						}
						if(o=='baoxianmiaoshu'){
							this.ruleForm.baoxianmiaoshu = obj[o];
							this.ro.baoxianmiaoshu = true;
							continue;
						}
						if(o=='baoxianfeiyong'){
							this.ruleForm.baoxianfeiyong = obj[o];
							this.ro.baoxianfeiyong = true;
							continue;
						}
						if(o=='baoxianfujian'){
							this.ruleForm.baoxianfujian = obj[o];
							this.ro.baoxianfujian = true;
							continue;
						}
						if(o=='qichetupian'){
							this.ruleForm.qichetupian = obj[o];
							this.ro.qichetupian = true;
							continue;
						}
						if(o=='yonghuzhanghao'){
							this.ruleForm.yonghuzhanghao = obj[o];
							this.ro.yonghuzhanghao = true;
							continue;
						}
						if(o=='yonghuxingming'){
							this.ruleForm.yonghuxingming = obj[o];
							this.ro.yonghuxingming = true;
							continue;
						}
						if(o=='cheshangzhanghao'){
							this.ruleForm.cheshangzhanghao = obj[o];
							this.ro.cheshangzhanghao = true;
							continue;
						}
					}
				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(this.$storage.get("role")!="管理员") {
							this.ro.banlishijian = true;
						}
						if(((json.cheshangzhanghao!=''&&json.cheshangzhanghao) || json.cheshangzhanghao==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.cheshangzhanghao = json.cheshangzhanghao
							this.ro.cheshangzhanghao = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
				this.$http({
					url: `option/baoxianleixing/baoxianleixing`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.baoxianleixingOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `cheliangbaoxian/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					this.ruleForm.ispay = '未支付'
					if(this.ruleForm.baodanbianhao) {
						this.ruleForm.baodanbianhao = String(this.ruleForm.baodanbianhao)
					}
					if(this.ruleForm.baoxianfujian!=null) {
						this.ruleForm.baoxianfujian = this.ruleForm.baoxianfujian.replace(new RegExp(this.$base.url,"g"),"");
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
								url: `cheliangbaoxian/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											if(this.isAuth('cheliangbaoxian','支付')&&this.type=='cross') {
												this.$confirm('是否跳转支付？').then(_ => {
													this.parent.showFlag = true;
													this.parent.addOrUpdateFlag = false;
													this.parent.cheliangbaoxianCrossAddOrUpdateFlag = false;
													this.$router.push('/cheliangbaoxian')
												}).catch(_ => {
													this.parent.showFlag = true;
													this.parent.addOrUpdateFlag = false;
													this.parent.cheliangbaoxianCrossAddOrUpdateFlag = false;
													this.parent.search();
													this.parent.contentStyleChange();
												});
											}else {
												this.parent.showFlag = true;
												this.parent.addOrUpdateFlag = false;
												this.parent.cheliangbaoxianCrossAddOrUpdateFlag = false;
												this.parent.search();
												this.parent.contentStyleChange();
											}
											
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
				this.parent.cheliangbaoxianCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			baoxianfujianUploadChange(fileUrls) {
				this.ruleForm.baoxianfujian = fileUrls;
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
