<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="80px"
			>
			<el-form-item class="add-item" label="车商账号" prop="cheshangzhanghao">
				<el-input v-model="ruleForm.cheshangzhanghao" 
					placeholder="车商账号" clearable :disabled=" false  ||ro.cheshangzhanghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="密码" prop="mima">
				<el-input v-model="ruleForm.mima" 
					placeholder="密码" clearable :disabled=" false  ||ro.mima"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="车商姓名" prop="cheshangxingming">
				<el-input v-model="ruleForm.cheshangxingming" 
					placeholder="车商姓名" clearable :disabled=" false  ||ro.cheshangxingming"></el-input>
			</el-form-item>
			<el-form-item class="add-item"  label="性别" prop="xingbie">
				<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" :disabled=" false  ||ro.xingbie" >
					<el-option
						v-for="(item,index) in xingbieOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item" label="手机" prop="shouji">
				<el-input v-model="ruleForm.shouji" 
					placeholder="手机" clearable :disabled=" false  ||ro.shouji"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="身份证" prop="shenfenzheng">
				<el-input v-model="ruleForm.shenfenzheng" 
					placeholder="身份证" clearable :disabled=" false  ||ro.shenfenzheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="资质文件" prop="zizhiwenjian">
				<file-upload
					tip="点击上传资质文件"
					action="file/upload"
					:limit="1"
					:type="3"
					:multiple="true"
					:fileUrls="ruleForm.zizhiwenjian?ruleForm.zizhiwenjian:''"
					@change="zizhiwenjianUploadChange"
					></file-upload>
			</el-form-item>  
			<el-form-item class="add-item" label="头像" v-if="type!='cross' || (type=='cross' && !ro.touxiang)" prop="touxiang">
				<file-upload
					tip="点击上传头像"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
					@change="touxiangUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="头像" prop="touxiang">
				<img v-if="ruleForm.touxiang.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.touxiang.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.touxiang.split(',')" :src="baseUrl+item">
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
					cheshangzhanghao : false,
					mima : false,
					cheshangxingming : false,
					xingbie : false,
					shouji : false,
					shenfenzheng : false,
					zizhiwenjian : false,
					touxiang : false,
					sfsh : false,
					shhf : false,
				},
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					cheshangzhanghao: '',
					mima: '',
					cheshangxingming: '',
					xingbie: '',
					shouji: '',
					shenfenzheng: '',
					zizhiwenjian: '',
					touxiang: '',
				},
				xingbieOptions: [],


				rules: {
					cheshangzhanghao: [
						{ required: true, message: '车商账号不能为空', trigger: 'blur' },
					],
					mima: [
						{ required: true, message: '密码不能为空', trigger: 'blur' },
					],
					cheshangxingming: [
						{ required: true, message: '车商姓名不能为空', trigger: 'blur' },
					],
					xingbie: [
						{ required: true, message: '性别不能为空', trigger: 'blur' },
					],
					shouji: [
						{ required: true, message: '手机不能为空', trigger: 'blur' },
						{ validator: this.$validate.isMobile, trigger: 'blur' },
					],
					shenfenzheng: [
						{ required: true, message: '身份证不能为空', trigger: 'blur' },
						{ validator: this.$validate.isIdCard, trigger: 'blur' },
					],
					zizhiwenjian: [
						{ required: true, message: '资质文件不能为空', trigger: 'blur' },
					],
					touxiang: [
					],
					sfsh: [
					],
					shhf: [
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
						if(o=='cheshangzhanghao'){
							this.ruleForm.cheshangzhanghao = obj[o];
							this.ro.cheshangzhanghao = true;
							continue;
						}
						if(o=='mima'){
							this.ruleForm.mima = obj[o];
							this.ro.mima = true;
							continue;
						}
						if(o=='cheshangxingming'){
							this.ruleForm.cheshangxingming = obj[o];
							this.ro.cheshangxingming = true;
							continue;
						}
						if(o=='xingbie'){
							this.ruleForm.xingbie = obj[o];
							this.ro.xingbie = true;
							continue;
						}
						if(o=='shouji'){
							this.ruleForm.shouji = obj[o];
							this.ro.shouji = true;
							continue;
						}
						if(o=='shenfenzheng'){
							this.ruleForm.shenfenzheng = obj[o];
							this.ro.shenfenzheng = true;
							continue;
						}
						if(o=='zizhiwenjian'){
							this.ruleForm.zizhiwenjian = obj[o];
							this.ro.zizhiwenjian = true;
							continue;
						}
						if(o=='touxiang'){
							this.ruleForm.touxiang = obj[o].split(",")[0];
							this.ro.touxiang = true;
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
					}
				});
				this.xingbieOptions = "男,女".split(',')

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
				this.$http.get(`cheshang/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
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


						await this.$http.post(`cheshang/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
							if (res.data.code == 0) {
								this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										this.$router.go(-1);
										
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
			zizhiwenjianUploadChange(fileUrls) {
				this.ruleForm.zizhiwenjian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
			touxiangUploadChange(fileUrls) {
				this.ruleForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
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
