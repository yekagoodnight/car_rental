<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="80px"
			>
			<el-form-item class="add-item" label="保单编号" prop="baodanbianhao">
				<el-input v-model="ruleForm.baodanbianhao" placeholder="保单编号" disabled></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="汽车车牌" prop="qichechepai">
				<el-input v-model="ruleForm.qichechepai" 
					placeholder="汽车车牌" clearable :disabled=" false  ||ro.qichechepai"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="汽车类型" prop="qicheleixing">
				<el-input v-model="ruleForm.qicheleixing" 
					placeholder="汽车类型" clearable :disabled=" false  ||ro.qicheleixing"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="品牌" prop="pinpai">
				<el-input v-model="ruleForm.pinpai" 
					placeholder="品牌" clearable :disabled=" false  ||ro.pinpai"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="型号" prop="xinghao">
				<el-input v-model="ruleForm.xinghao" 
					placeholder="型号" clearable :disabled=" false  ||ro.xinghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="保险名称" prop="baoxianmingcheng">
				<el-input v-model="ruleForm.baoxianmingcheng" 
					placeholder="保险名称" clearable :disabled=" false  ||ro.baoxianmingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item"  label="保险类型" prop="baoxianleixing">
				<el-select v-model="ruleForm.baoxianleixing" placeholder="请选择保险类型" :disabled=" false  ||ro.baoxianleixing" >
					<el-option
						v-for="(item,index) in baoxianleixingOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item" label="办理时间" prop="banlishijian">
				<el-date-picker
					:disabled="true  ||ro.banlishijian"
					value-format="yyyy-MM-dd HH:mm:ss"
					v-model="ruleForm.banlishijian" 
					type="datetime"
					placeholder="办理时间">
				</el-date-picker>
			</el-form-item>
			<el-form-item class="add-item" label="到期时间" prop="daoqishijian">
				<el-date-picker
					:disabled=" false  ||ro.daoqishijian"
					value-format="yyyy-MM-dd HH:mm:ss"
					v-model="ruleForm.daoqishijian" 
					type="datetime"
					:picker-options="daoqishijianPickerOptions"
					placeholder="到期时间">
				</el-date-picker>
			</el-form-item>
			<el-form-item class="add-item" label="保险费用" prop="baoxianfeiyong">
				<el-input v-model.number="ruleForm.baoxianfeiyong" 
					placeholder="保险费用" clearable :disabled=" false  ||ro.baoxianfeiyong"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="保险附件" prop="baoxianfujian">
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
			<el-form-item class="add-item" label="汽车图片" v-if="type!='cross' || (type=='cross' && !ro.qichetupian)" prop="qichetupian">
				<file-upload
					tip="点击上传汽车图片"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:fileUrls="ruleForm.qichetupian?ruleForm.qichetupian:''"
					@change="qichetupianUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="汽车图片" prop="qichetupian">
				<img v-if="ruleForm.qichetupian.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.qichetupian.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.qichetupian.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item" label="用户账号" prop="yonghuzhanghao">
				<el-input v-model="ruleForm.yonghuzhanghao" 
					placeholder="用户账号" clearable :disabled=" false  ||ro.yonghuzhanghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="用户姓名" prop="yonghuxingming">
				<el-input v-model="ruleForm.yonghuxingming" 
					placeholder="用户姓名" clearable :disabled=" false  ||ro.yonghuxingming"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="车商账号" prop="cheshangzhanghao">
				<el-input v-model="ruleForm.cheshangzhanghao" 
					placeholder="车商账号" clearable :disabled=" false  ||ro.cheshangzhanghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="保险描述" prop="baoxianmiaoshu">
				<el-input
					type="textarea"
					:rows="8"
					placeholder="保险描述"
					v-model="ruleForm.baoxianmiaoshu">
					</el-input>
			</el-form-item>

			<el-form-item class="add-btn-item">
				<el-button class="submitBtn"  type="primary" @click="onSubmit">
					<span class="icon iconfont icon-kaitongfuwu"></span>
					<span class="text">更新</span>
				</el-button>
				<el-button class="closeBtn" @click="back()">
					<span class="icon iconfont icon-shanchu1"></span>
					<span class="text">取消</span>
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				baseUrl: '',
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
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
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

				// 只能选择今天和之后日期
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
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
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
				centerType: false,
			};
		},
		computed: {



		},
		components: {
		},
		created() {
			if(this.$route.query.centerType){
				this.centerType = true
			}
			//this.bg();
			let type = this.$route.query.type ? this.$route.query.type : '';
			this.init(type);
			this.baseUrl = this.$config.baseUrl;
			this.ruleForm.banlishijian = this.getCurDateTime()
		},
		methods: {
			getMakeZero(s) {
				return s < 10 ? '0' + s : s;
			},
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(type) {
				this.type = type;
				if(type=='cross'){
					var obj = JSON.parse(localStorage.getItem('crossObj'));
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
							this.ruleForm.qichetupian = obj[o].split(",")[0];
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
				}else if(type=='edit'){
					this.info()
				}
				// 获取用户信息
				this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						var json = res.data.data;
						if((json.cheshangzhanghao!=''&&json.cheshangzhanghao) || json.cheshangzhanghao==0){
							this.ruleForm.cheshangzhanghao = json.cheshangzhanghao;
							this.ro.cheshangzhanghao = true;
						}
					}
				});
				this.$http.get('option/baoxianleixing/baoxianleixing', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.baoxianleixingOptions = res.data.data;
					}
				});

				if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
					localStorage.removeItem('raffleType')
					setTimeout(() => {
						this.onSubmit()
					}, 300)
				}
			},

			// 多级联动参数
			// 多级联动参数
			info() {
				this.$http.get(`cheliangbaoxian/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
				if(this.ruleForm.baodanbianhao){
					this.ruleForm.baodanbianhao = String(this.ruleForm.baodanbianhao)
				}
				await this.$refs["ruleForm"].validate(async valid => {
					if(valid) {
						if(this.type=='cross'){
							var statusColumnName = localStorage.getItem('statusColumnName');
							var statusColumnValue = localStorage.getItem('statusColumnValue');
							if(statusColumnName && statusColumnName!='') {
								var obj = JSON.parse(localStorage.getItem('crossObj'));
								if(!statusColumnName.startsWith("[")) {
									for (var o in obj){
										if(o==statusColumnName){
											obj[o] = statusColumnValue;
										}
									}
									var table = localStorage.getItem('crossTable');
									await this.$http.post(table+'/update', obj).then(res => {});
								}
							}
						}


						await this.$http.post(`cheliangbaoxian/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
							if (res.data.code == 0) {
								this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										if(this.isBackAuth('cheliangbaoxian','支付')&&this.type=='cross'){
											this.$confirm('是否跳转支付？') .then(_ => {
												let jumpParams = {
													id: res.data.data,
													centerType: 1
												}
												this.$router.push({path: '/index/cheliangbaoxianDetail', query: jumpParams});
											}).catch(_ => {
												this.$router.go(-1);
											});
										}else {
											this.$router.go(-1);
										}
										
										
									}
								});
							} else {
								this.$message({
									message: res.data.msg,
									type: 'error',
									duration: 1500
								});
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
				this.$router.go(-1);
			},
			baoxianfujianUploadChange(fileUrls) {
				this.ruleForm.baoxianfujian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
			qichetupianUploadChange(fileUrls) {
				this.ruleForm.qichetupian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
		}
	};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.add-update-preview {
		padding: 0 16%;
		margin: 10px auto;
		background: none;
		width: 100%;
		position: relative;
		.add-update-form {
			padding: 20px 0;
			background: #fff;
			width: 100%;
			position: relative;
			.add-item.el-form-item {
				padding: 10px;
				margin: 0 0 10px;
				background: none;
				display: inline-block;
				width: 49%;
				/deep/ .el-form-item__label {
					padding: 0 10px 0 0;
					color: #4B3E39;
					font-weight: 500;
					width: 80px;
					font-size: 14px;
					line-height: 40px;
					text-align: right;
				}
				/deep/ .el-form-item__content {
					margin-left: 80px;
				}
				.el-input {
					width: 80%;
				}
				.el-input /deep/ .el-input__inner {
					border: 0;
					border-radius: 10px;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					background: linear-gradient(135.00deg, rgb(232, 240, 255) 0%,rgb(244, 248, 253) 100%);
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-input /deep/ .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 10px;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: linear-gradient(135.00deg, rgb(232, 240, 255) 0%,rgb(244, 248, 253) 100%);
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input__inner {
					text-align: left;
					border: 0;
					border-radius: 10px;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					background: linear-gradient(135.00deg, rgb(232, 240, 255) 0%,rgb(244, 248, 253) 100%);
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-input-number /deep/ .is-disabled .el-input__inner {
					text-align: left;
					border: 0;
					cursor: not-allowed;
					border-radius: 10px;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: linear-gradient(135.00deg, rgb(232, 240, 255) 0%,rgb(244, 248, 253) 100%);
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input-number__decrease {
					display: none;
				}
				.el-input-number /deep/ .el-input-number__increase {
					display: none;
				}
				.el-select {
					width: 80%;
				}
				.el-select /deep/ .el-input__inner {
					border: 0;
					border-radius: 10px;
					padding: 0 10px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					background: linear-gradient(135.00deg, rgb(232, 240, 255) 0%,rgb(244, 248, 253) 100%);
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-select /deep/ .is-disabled .el-input__inner {
					border: 0;
					cursor: not-allowed;
					border-radius: 10px;
					padding: 0 10px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: linear-gradient(135.00deg, rgb(232, 240, 255) 0%,rgb(244, 248, 253) 100%);
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-date-editor {
					width: 80%;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 0;
					border-radius: 10px;
					padding: 0 10px 0 30px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					background: linear-gradient(135.00deg, rgb(232, 240, 255) 0%,rgb(244, 248, 253) 100%);
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 10px;
					padding: 0 10px 0 30px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: linear-gradient(135.00deg, rgb(232, 240, 255) 0%,rgb(244, 248, 253) 100%);
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				/deep/ .el-upload--picture-card {
					background: transparent;
					border: 0;
					border-radius: 0;
					width: auto;
					height: auto;
					line-height: initial;
					vertical-align: middle;
				}
				/deep/ .upload .upload-img {
					border: 0px dashed rgba(64, 158, 255, 1);
					cursor: pointer;
					border-radius: 6px;
					color: #23469A;
					background: linear-gradient(135.00deg, rgb(232, 240, 255) 0%,rgb(244, 248, 253) 100%);
					width: 100px;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
					height: 100px;
				}
				/deep/ .el-upload-list .el-upload-list__item {
					border: 0px dashed rgba(64, 158, 255, 1);
					cursor: pointer;
					border-radius: 6px;
					color: #23469A;
					background: linear-gradient(135.00deg, rgb(232, 240, 255) 0%,rgb(244, 248, 253) 100%);
					width: 100px;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
					height: 100px;
					font-size: 14px;
					line-height: 1.8;
				}
				/deep/ .el-upload .el-icon-plus {
					border: 0px dashed rgba(64, 158, 255, 1);
					cursor: pointer;
					border-radius: 6px;
					color: #23469A;
					background: linear-gradient(135.00deg, rgb(232, 240, 255) 0%,rgb(244, 248, 253) 100%);
					width: 100px;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
					height: 100px;
				}
				/deep/ .el-upload__tip {
					color: #838fa1;
					display: none;
				}
				.el-textarea /deep/ .el-textarea__inner {
					border: 0;
					border-radius: 10px;
					padding: 12px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					background: linear-gradient(135.00deg, rgb(232, 240, 255) 0%,rgb(244, 248, 253) 100%);
					width: 80%;
					font-size: 14px;
					height: 120px;
				}
				.el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 10px;
					padding: 12px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: linear-gradient(135.00deg, rgb(232, 240, 255) 0%,rgb(244, 248, 253) 100%);
					width: 80%;
					font-size: 14px;
					height: 120px;
				}
				/deep/ .el-input__inner::placeholder {
					color: #6e6e6e;
					font-size: 14px;
				}
				/deep/ textarea::placeholder {
					color: #6e6e6e;
					font-size: 14px;
				}
				.editor {
					background-color: #fff;
					border-radius: 0;
					padding: 0;
					box-shadow: 0 0 0px rgba(75,223,201,.5);
					margin: 0;
					width: 100%;
					border-color: #ccc;
					border-width: 0;
					border-style: solid;
					height: auto;
				}
				.upload-img {
					width: 100px;
					height: 100px;
				}
				.viewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 10px;
					margin: 0;
					color: #fff;
					display: inline-block;
					font-size: 14px;
					line-height: 30px;
					border-radius: 4px;
					outline: none;
					background: #23469A;
					width: auto;
					height: 30px;
				}
				.viewBtn:hover {
					color: #fff;
					background: #23469A;
				}
				.unviewBtn {
					border: 0;
					cursor: not-allowed;
					padding: 0 10px;
					margin: 0;
					color: #E8D6B0;
					display: inline-block;
					font-size: 14px;
					line-height: 30px;
					border-radius: 4px;
					outline: none;
					background: linear-gradient(180.00deg, rgb(242, 229, 195),rgb(253, 245, 222) 98.473%);
					width: auto;
					height: 30px;
				}
				.unviewBtn:hover {
					color: #E8D6B0;
					background: linear-gradient(180.00deg, rgb(242, 229, 195),rgb(253, 245, 222) 98.473%);
				}
			}
			.add-btn-item {
				padding: 0;
				margin: 0;
				.submitBtn {
					border: 0;
					cursor: pointer;
					border-radius: 0;
					padding: 0 15px;
					margin: 0 20px 0 0;
					outline: none;
					background: #23469A;
					display: inline-block;
					width: auto;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
					.icon {
						color: rgba(255, 255, 255, 1);
					}
					.text {
						color: rgba(255, 255, 255, 1);
					}
				}
				.submitBtn:hover {
					opacity: 0.7;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
				.closeBtn {
					border: 0px solid rgba(64, 158, 255, 1);
					cursor: pointer;
					border-radius: 4px;
					padding: 0 15px;
					margin: 0 20px 0 0;
					outline: none;
					background: #E8E8E8;
					display: inline-block;
					width: auto;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
					.icon {
						color: #6E6E6E;
					}
					.text {
						color: #6E6E6E;
					}
				}
				.closeBtn:hover {
					opacity: 0.7;
					.icon {
						color: #6E6E6E;
					}
					.text {
						color: #6E6E6E;
					}
				}
			}
		}
	}
	.el-date-editor.el-input {
		width: auto;
	}
</style>
