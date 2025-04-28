<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="80px"
			>
			<el-form-item class="add-item" label="订单编号" prop="dingdanbianhao">
				<el-input v-model="ruleForm.dingdanbianhao" 
					placeholder="订单编号" clearable :disabled=" false  ||ro.dingdanbianhao"></el-input>
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
			<el-form-item class="add-item" label="日租金" prop="rizujin">
				<el-input-number v-model="ruleForm.rizujin" placeholder="日租金" :disabled=" false ||ro.rizujin"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="租车时间" prop="zucheshijian">
				<el-date-picker
					:disabled="true  ||ro.zucheshijian"
					value-format="yyyy-MM-dd HH:mm:ss"
					v-model="ruleForm.zucheshijian" 
					type="datetime"
					placeholder="租车时间">
				</el-date-picker>
			</el-form-item>
			<el-form-item class="add-item" label="还车时间" prop="haicheshijian">
				<el-date-picker
					:disabled="true  ||ro.haicheshijian"
					value-format="yyyy-MM-dd HH:mm:ss"
					v-model="ruleForm.haicheshijian" 
					type="datetime"
					placeholder="还车时间">
				</el-date-picker>
			</el-form-item>
			<el-form-item class="add-item" label="还车备注" prop="haichebeizhu">
				<el-input v-model="ruleForm.haichebeizhu" 
					placeholder="还车备注" clearable :disabled=" false  ||ro.haichebeizhu"></el-input>
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
			<el-form-item class="add-item" label="车商账号" prop="cheshangzhanghao">
				<el-input v-model="ruleForm.cheshangzhanghao" 
					placeholder="车商账号" clearable :disabled=" false  ||ro.cheshangzhanghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="用户账号" prop="yonghuzhanghao">
				<el-input v-model="ruleForm.yonghuzhanghao" 
					placeholder="用户账号" clearable :disabled=" false  ||ro.yonghuzhanghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="用户姓名" prop="yonghuxingming">
				<el-input v-model="ruleForm.yonghuxingming" 
					placeholder="用户姓名" clearable :disabled=" false  ||ro.yonghuxingming"></el-input>
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
					dingdanbianhao : false,
					qichechepai : false,
					qicheleixing : false,
					pinpai : false,
					xinghao : false,
					rizujin : false,
					zucheshijian : false,
					haicheshijian : false,
					haichebeizhu : false,
					qichetupian : false,
					cheshangzhanghao : false,
					yonghuzhanghao : false,
					yonghuxingming : false,
					crossuserid : false,
					crossrefid : false,
					sfsh : false,
					shhf : false,
				},
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					dingdanbianhao: '',
					qichechepai: '',
					qicheleixing: '',
					pinpai: '',
					xinghao: '',
					rizujin: '',
					zucheshijian: '',
					haicheshijian: '',
					haichebeizhu: '',
					qichetupian: '',
					cheshangzhanghao: '',
					yonghuzhanghao: '',
					yonghuxingming: '',
					crossuserid: '',
					crossrefid: '',
				},


				rules: {
					dingdanbianhao: [
					],
					qichechepai: [
					],
					qicheleixing: [
					],
					pinpai: [
					],
					xinghao: [
					],
					rizujin: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					zucheshijian: [
					],
					haicheshijian: [
					],
					haichebeizhu: [
						{ required: true, message: '还车备注不能为空', trigger: 'blur' },
					],
					qichetupian: [
					],
					cheshangzhanghao: [
					],
					yonghuzhanghao: [
					],
					yonghuxingming: [
					],
					crossuserid: [
					],
					crossrefid: [
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
			tianshu : {
				get: function () {
					let d = this.ruleForm
					let a = 'd2.zucheshijian-d2.haicheshijian'
					let n = a.split('-')
					let day = this.getFullDay(d[n[0].split('d2.')[1]], d[n[1].split('d2.')[1]])
					this.ruleForm.tianshu = day?day:0
					return day?day:0
				}
				
			},
			zongjia : {
				get: function () {
					let c = this.ruleForm
					let a = c.rizujin*c.tianshu-c.yajin
					this.ruleForm.zongjia = a?Number(Number(a).toFixed(2)):0
					return a?Number(Number(a).toFixed(2)):0
				}
				
			},



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
			this.ruleForm.zucheshijian = this.getCurDateTime()
			this.ruleForm.haicheshijian = this.getCurDateTime()
		},
		methods: {
			// 获取日期间隔 单位天
			getFullDay(first, last) {
				let date1 = new Date(first)
				let date2 = new Date(last)
				let a = date1.getTime();
				let b = date2.getTime();
				var count = 0;
				for (var i = a; i <= b; i += 24 * 3600 * 1000) {
					count++;
				}
				return count;
			},
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
						if(o=='dingdanbianhao'){
							this.ruleForm.dingdanbianhao = obj[o];
							this.ro.dingdanbianhao = true;
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
						if(o=='rizujin'){
							this.ruleForm.rizujin = obj[o];
							this.ro.rizujin = true;
							continue;
						}
						if(o=='zucheshijian'){
							this.ruleForm.zucheshijian = obj[o];
							this.ro.zucheshijian = true;
							continue;
						}
						if(o=='haicheshijian'){
							this.ruleForm.haicheshijian = obj[o];
							this.ro.haicheshijian = true;
							continue;
						}
						if(o=='haichebeizhu'){
							this.ruleForm.haichebeizhu = obj[o];
							this.ro.haichebeizhu = true;
							continue;
						}
						if(o=='qichetupian'){
							this.ruleForm.qichetupian = obj[o].split(",")[0];
							this.ro.qichetupian = true;
							continue;
						}
						if(o=='cheshangzhanghao'){
							this.ruleForm.cheshangzhanghao = obj[o];
							this.ro.cheshangzhanghao = true;
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
						if(o=='crossuserid'){
							this.ruleForm.crossuserid = obj[o];
							this.ro.crossuserid = true;
							continue;
						}
						if(o=='crossrefid'){
							this.ruleForm.crossrefid = obj[o];
							this.ro.crossrefid = true;
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
						if((json.yonghuzhanghao!=''&&json.yonghuzhanghao) || json.yonghuzhanghao==0){
							this.ruleForm.yonghuzhanghao = json.yonghuzhanghao;
							this.ro.yonghuzhanghao = true;
						}
						if((json.yonghuxingming!=''&&json.yonghuxingming) || json.yonghuxingming==0){
							this.ruleForm.yonghuxingming = json.yonghuxingming;
							this.ro.yonghuxingming = true;
						}
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
				this.$http.get(`haichejilu/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
				if(this.ruleForm.tianshu==0){
					return false
				}
				if(this.ruleForm.zongjia==0){
					return false
				}
				//更新跨表属性
				var crossuserid;
				var crossrefid;
				var crossoptnum;
				var finishNum = 0;
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
								else {
									crossuserid=Number(localStorage.getItem('frontUserid'));
									crossrefid=obj['id'];
									crossoptnum=localStorage.getItem('statusColumnName');
									crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
								}
							}
							if(crossrefid && crossuserid) {
								this.ruleForm.crossuserid=crossuserid;
								this.ruleForm.crossrefid=crossrefid;
								var params = {
									page: 1,
									limit: 10,
									crossuserid:crossuserid,
									crossrefid:crossrefid,
								}
								await this.$http.get('haichejilu/list', {
									params: params
								}).then(res => {
									finishNum = res.data.data.total
								});
							}
							if(finishNum>=crossoptnum) {
								this.$message({
									message: localStorage.getItem('tips'),
									type: 'error',
									duration: 1500,
								});
								return false;
							}
						}


						await this.$http.post(`haichejilu/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
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
