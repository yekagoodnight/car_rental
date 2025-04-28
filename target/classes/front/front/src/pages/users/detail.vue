<template>
	<div>
	<!--  -->
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="''">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index" :to="'/index/users?centerType=' + (centerType?'1':'0')"><a>{{item.name}}</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item3"><a href="javascript:void(0);">详情</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="detail-preview">
			<div class="attr">
				<div class="info">
					<div class="title-item">
						<div class="detail-title">
						</div>
					</div>
					<div class="item">
						<div class="lable">用户名</div>
						<div class="text "  >{{detail.username}}</div>
					</div>
					<div class="item">
						<div class="lable">角色</div>
						<div class="text "  >{{detail.role}}</div>
					</div>
					<div class="btn_box">
						<el-button class="editBtn" v-if="btnAuth('users','修改')" @click="editClick">修改</el-button>
						<el-button class="delBtn" v-if="btnAuth('users','删除')" @click="delClick">删除</el-button>
					</div>
				</div>
			</div>
		
			<div class="swiper3" v-if="detailBanner.length">
				<div class="big">
					<img id="big" :src="swiperBigUrl" class="image">
				</div>
				<div class="samll">
					<div class="swiper3-small-item" v-for="item in detailBanner" :key="item.id">
						<img v-if="item.substr(0,4)=='http'" :src="item" @click="swiperClick3(item)" class="image">
						<img v-else :src="baseUrl + item" @click="swiperClick3(baseUrl + item)" class="image">
					</div>
				</div>
			</div>


		

			<el-tabs class="detail-tabs" v-model="activeName" type="border-card" v-if="tabsNum>0" >
			</el-tabs>

		</div>
	</div>
</template>

<script>
	import axios from 'axios'
	import Swiper from "swiper";
	export default {
		//数据集合
		data() {
			return {
				tablename: 'users',
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '管理员'
					}
				],
				title: '',
				detailBanner: [],
				userid: localStorage.getItem('frontUserid'),
				id: 0,
				detail: {},
				tabsNum: 0,
				activeName: 'first',
				total: 1,
				pageSize: 10,
				totalPage: 1,
				buynumber: 1,
				centerType: false,
				storeupType: false,
				swiperBigUrl: null,
				sensitiveWordsArr: [],
			}
		},
		created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0) {
				this.centerType = true
			}
			if(this.$route.query.storeupType&&this.$route.query.storeupType!=0) {
				this.storeupType = true
			}
			
			this.init();
		},
		mounted() {
		},
		//方法集合
		methods: {
			swiperClick3(src) {
				this.swiperBigUrl = src
			},
			init() {
				this.id = this.$route.query.id
				this.baseUrl = this.$config.baseUrl;
				this.$http.get(this.tablename + '/detail/'  + this.id, {}).then(res => {
					if (res.data.code == 0) {
						this.detail = res.data.data;
						// 获取数据库敏感词
						this.getSensitiveWords()
						this.$forceUpdate();
						if(localStorage.getItem('frontToken')){
						}

					}
					if (this.detailBanner.length) {
						if (this.detailBanner[0].substr(0,4)=='http') {
							this.swiperBigUrl = this.detailBanner[0]
						} else {
							this.swiperBigUrl = this.baseUrl + this.detailBanner[0]
						}
					}
				});
			},
			getSensitiveWords(){
				this.$http.get('sensitivewords/detail/1').then(rs=>{
					this.sensitiveWordsArr = rs.data.data.content.split(',')
				})
			},
			curChange(page) {
				this.getDiscussList(page);
			},
			prevClick(page) {
				this.getDiscussList(page);
			},
			nextClick(page) {
				this.getDiscussList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getDiscussList(1);
			},
			// 返回按钮
			backClick(){
				if(this.storeupType){
					history.back()
				}else{
					let params = {}
					if(this.centerType){
						params.centerType = 1
					}
					this.$router.push({path: '/index/users', query: params});
				}
			},
			// 下载
			download(file ){
				if(!file) {
					this.$message({
						type: 'error',
						message: '文件不存在',
						duration: 1500,
					});
					return;
				}
				let arr = file.replace(new RegExp('upload/', "g"), "")
				axios.get(this.baseUrl + '/file/download?fileName=' + arr, {
					headers: {
						token: localStorage.getItem("frontToken")
					},
					responseType: "blob"
				}).then(({
					data
				}) => {
					const binaryData = [];
					binaryData.push(data);
					const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
						type: 'application/pdf;chartset=UTF-8'
					}))
					const a = document.createElement('a')
					a.href = objectUrl
					a.download = arr
					// a.click()
					// 下面这个写法兼容火狐
					a.dispatchEvent(new MouseEvent('click', {
						bubbles: true,
						cancelable: true,
						view: window
					}))
					window.URL.revokeObjectURL(data)
				},err=>{
					axios.get((location.href.split(this.$config.name).length>1 ? location.href.split(this.$config.name)[0] :'') + this.$config.name + '/file/download?fileName=' + arr, {
						headers: {
							token: localStorage.getItem("frontToken")
						},
						responseType: "blob"
					}).then(({
						data
					}) => {
						const binaryData = [];
						binaryData.push(data);
						const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
							type: 'application/pdf;chartset=UTF-8'
						}))
						const a = document.createElement('a')
						a.href = objectUrl
						a.download = arr
						// a.click()
						// 下面这个写法兼容火狐
						a.dispatchEvent(new MouseEvent('click', {
							bubbles: true,
							cancelable: true,
							view: window
						}))
						window.URL.revokeObjectURL(data)
					})
				})
			},


			// 权限判断
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			// 修改
			editClick(){
				this.$router.push(`/index/usersAdd?type=edit&&id=${this.detail.id}`);
			},
			// 删除
			async delClick(){
				await this.$confirm('是否删除此管理员？') .then(_ => {
					this.$http.post('users/delete', [this.detail.id]).then(async res => {
						if (res.data.code == 0) {
							this.$message({
								type: 'success',
								message: '删除成功!',
								duration: 1500,
								onClose: () => {
									history.back()
								}
							});
						}
					});
				}).catch(_ => {});
			},
		},
		components: {
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.detail-preview {
		padding: 0 16%;
		margin: 0px auto;
		display: flex;
		width: 100%;
		position: relative;
		flex-wrap: wrap;
		.attr {
			padding: 10px 0;
			background: #fff;
			display: flex;
			width: 100%;
			position: relative;
			order: 3;
			.info {
				padding: 10px 0;
				margin: 0 0 0 10px;
				background: #fff;
				flex: 1;
				display: flex;
				justify-content: space-between;
				flex-wrap: wrap;
				.title-item {
					padding: 10px;
					margin: 0 0 10px 0;
					background: linear-gradient(0.00deg, rgb(145, 177, 255) 0%,rgb(255, 255, 255) 24.428%,rgb(255, 255, 255) 100%);
					display: flex;
					width: 48%;
					border-color: #25489C80;
					border-width: 0 0 2px;
					justify-content: space-between;
					align-items: center;
					border-style: solid;
					.detail-title {
						color: #333;
						font-size: 16px;
					}
				}
				.item {
					padding: 10px;
					margin: 0 0 10px 0;
					background: linear-gradient(0.00deg, rgb(145, 177, 255) 0%,rgb(255, 255, 255) 24.428%,rgb(255, 255, 255) 100%);
					display: flex;
					width: 48%;
					border-color: #25489C80;
					border-width: 0 0 2px;
					justify-content: flex-start;
					align-items: center;
					border-style: solid;
					.lable {
						padding: 0 10px;
						color: #000;
						white-space: nowrap;
						width: auto;
						font-size: 13px;
						line-height: 24px;
						text-align: left;
						height: auto;
					}
					.text {
						padding: 0 10px;
						color: #25489C;
						word-break: break-all;
						flex: 1;
						font-size: 14px;
						line-height: 24px;
						height: auto;
					}
					.price {
						color: #f00;
					}
					.bold {
						font-weight: bold;
					}
					.link {
						cursor: pointer;
						text-decoration: underline;
					}
				}
				.btn_box {
					padding: 10px 0;
					display: flex;
					width: 100%;
					align-items: center;
					flex-wrap: wrap;
				}
				.editBtn {
					border: 0;
					cursor: pointer;
					border-radius: 0;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #fff;
					background: #007ED0;
					width: auto;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
				}
				.editBtn:hover {
					opacity: 0.7;
				}
				.delBtn {
					border: 0;
					cursor: pointer;
					border-radius: 0;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #fff;
					background: #218BD0;
					width: auto;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
				}
				.delBtn:hover {
					opacity: 0.7;
				}
			}
		}
		.swiper3 {
			background: none;
			display: flex;
			width: calc(100% - 200px);
			align-items: flex-start;
			height: auto;
			order: 1;
			.big {
				border: 0px solid red;
				margin: 0 20px 0 0;
				background: #fff;
				width: 100%;
				position: relative;
				height: 400px;
				img {
					object-fit: cover;
					display: block;
					width: 100%;
					height: 100%;
				}
			}
			.samll {
				padding: 0 0 100px;
				flex-direction: column;
				background: none;
				display: flex;
				width: 0;
				height: 400px;
				.swiper3-small-item {
					border: 0px solid red;
					margin: 0 0 5px;
					background: none;
					flex: 1;
					width: 200px;
					position: relative;
					height: 20%;
					img {
						object-fit: cover;
						display: block;
						width: 100%;
						height: 100%;
					}
				}
			}
		}
		.detail-tabs {
			border: 0px solid #DCDFE6;
			box-shadow: 0 0px 0px 0 rgba(0, 0, 0, .1);
			background: #fff;
			width: 100%;
			order: 6;
			& /deep/ .el-tabs__header .el-tabs__nav-wrap {
				margin-bottom: 0;
			}
			/deep/ .el-tabs__header {
				padding: 10px;
				margin: 0;
				background: #fff;
				border-color: #23469A;
				border-width: 0 0 2px;
				border-style: solid;
			}
			
			/deep/ .el-tabs__header .el-tabs__item {
				border: 3px solid rgb(35, 70, 154);
				padding: 0 20px;
				margin: 0 10px 0 0;
				color: #23469A;
				font-weight: 500;
				display: inline-block;
				font-size: 14px;
				line-height: 40px;
				border-radius: 0 0 30px 0;
				background: linear-gradient(180.00deg, rgb(145, 177, 255),rgb(222, 229, 253) 98.473%);
				position: relative;
				list-style: none;
				height: auto;
			}
			
			/deep/ .el-tabs__header .el-tabs__item:hover {
				border: 3px solid rgb(232, 214, 176);
				border-radius: 0 0 30px 0;
				margin: 0 10px 0 0;
				color: #AD8B44;
				background: linear-gradient(180.00deg, rgb(242, 229, 195),rgb(253, 245, 222) 98.473%);
				line-height: 40px;
				height: auto;
			}
			
			/deep/ .el-tabs__header .el-tabs__item.is-active {
				border: 3px solid rgb(232, 214, 176);
				border-radius: 0 0 30px 0;
				margin: 0 10px 0 0;
				color: #AD8B44;
				background: linear-gradient(180.00deg, rgb(242, 229, 195),rgb(253, 245, 222) 98.473%);
				line-height: 40px;
				height: auto;
			}
			
			/deep/ .el-tabs__content {
				padding: 15px;
			}
		}
	}
</style>
