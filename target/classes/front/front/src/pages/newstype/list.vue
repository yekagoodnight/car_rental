<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="''">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div v-if="centerType" class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="list-preview">
			<el-form :inline="true" :model="formSearch" class="list-form-pv">
				<el-form-item class="list-item">
					<div class="lable">分类名称：</div>
					<el-input v-model="formSearch.typename" placeholder="分类名称" @keydown.enter.native="getList(1, curFenlei)" clearable></el-input>
				</el-form-item>
				<el-button class="list-search-btn" v-if=" true " type="primary" @click="getList(1, curFenlei)">
					查询
				</el-button>
				<el-button class="list-add-btn" v-if="btnAuth('newstype','新增')" type="primary" @click="add('/index/newstypeAdd')">
					添加
				</el-button>
			</el-form>
			<div class="select2">
				<div class="select2-list" v-for="(item,index) in selectOptionsList" :key="item">
					<div class="label">{{item.name}}：</div>
					<div class="item-body">
						<div class="item" @click="selectClick2(item,-1)" :class="item.check ==-1 ? 'active' : ''">全部</div>
						<div class="item" @click="selectClick2(item,index1)" :class="item.check == index1 ? 'active' : ''" v-for="item1,index1 in item.list" :key="item1">{{item1}}</div>
					</div>
				</div>
			</div>
			<div class="list">
				<!-- 样式一 -->
				<div class="list1 index-pv1">
					<div v-for="(item, index) in dataList" :key="index" @click.stop="toDetail(item)" class="list-item animation-box">
						<div class="time_item">
							<span class="icon iconfont icon-shijian21"></span>
							<span class="label">发布时间：</span>
							<span class="text">{{item.addtime.split(' ')[0]}}</span>
						</div>
					</div>
				</div>
			</div>

	
			<el-pagination
				background
				id="pagination"
				class="pagination"
				:pager-count="7"
				:page-size="pageSize"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="true"
				:layout='["prev","pager","next","jumper"].join()'
				:total="total"
				:page-sizes="pageSizes"
				@current-change="curChange"
				@size-change="sizeChange"
				@prev-click="prevClick"
				@next-click="nextClick"
				></el-pagination>
		</div>
		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>
<script>
	export default {
		//数据集合
		data() {
			return {
				selectIndex2: 0,
				selectOptionsList: [],
				layouts: '',
				swiperIndex: -1,
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '新闻资讯分类'
					}
				],
				formSearch: {
					typename: '',
				},
				fenlei: [],
				feileiColumn: '',
				dataList: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1,
				curFenlei: '全部',
				isPlain: false,
				indexQueryCondition: '',
				timeRange: [],
				centerType:false,
				previewImg: '',
				previewVisible: false,
				sortType: 'id',
				sortOrder: 'desc',
			}
		},
		async created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0){
				this.centerType = true
			}
			this.baseUrl = this.$config.baseUrl;
			await this.getFenlei();
			this.getList(1, '全部');
		},
		watch:{
			$route(newValue){
				this.getList(1, newValue.query.homeFenlei);
			}
		},
		//方法集合
		methods: {
			selectClick2(row,index) {
				row.check = index
				if(index == -1){
					this.formSearch[row.tableName] = ''
				}else {
					this.formSearch[row.tableName] = row.list[index]
				}
				this.getList()
			},
			add(path) {
				let query = {}
				if(this.centerType){
					query.centerType = 1
				}
				this.$router.push({path: path,query:query});
			},
			async getFenlei() {
			},
			getList(page, fenlei, ref = '') {
				let params = {
					page,
					limit: this.pageSize,
				};
				let searchWhere = {};
				if (this.formSearch.typename != '') searchWhere.typename = '%' + this.formSearch.typename + '%';
				let user = JSON.parse(localStorage.getItem('sessionForm'))
				if (this.sortType) searchWhere.sort = this.sortType
				if (this.sortOrder) searchWhere.order = this.sortOrder
				this.$http.get(`newstype/${this.centerType?'page':'list'}`, {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.dataList = res.data.data.list;
						this.total = Number(res.data.data.total);
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			sortClick(type){
				if(this.sortType==type){
					if(this.sortOrder == 'desc'){
						this.sortOrder = 'asc'
					}else{
						this.sortOrder = 'desc'
					}
				}else{
					this.sortType = type
					this.sortOrder = 'desc'
				}
				this.getList(1, '全部')
			},
			curChange(page) {
				this.getList(page);
			},
			prevClick(page) {
				this.getList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getList(1);
			},
			nextClick(page) {
				this.getList(page);
			},
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
			},
			toDetail(item) {
				let params = {
					id: item.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/newstypeDetail', query: params});
			},
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			backClick() {
				this.$router.push({path: '/index/center'});
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.list-preview {
		padding: 0 16%;
		margin: 10px auto;
		background: none;
		display: flex;
		width: 100%;
		position: relative;
		flex-wrap: wrap;
		.list-form-pv {
			padding: 10px;
			background: none;
			display: flex;
			width: 100%;
			align-items: center;
			flex-wrap: wrap;
			height: auto;
			order: 1;
			.list-item {
				margin: 0 10px 10px;
				/deep/.el-form-item__content {
					display: flex;
					width: 100%;
				}
				.lable {
					padding: 0;
					color: #25489C;
					white-space: nowrap;
					flex: 1;
					display: inline-block;
					width: auto;
					line-height: 42px;
				}
				.el-input {
					width: auto;
				}
				.datetimerange {
					border: 1px solid #25489C;
					border-radius: 0;
					padding: 3px 10px;
					outline: none;
					background: #fff;
					width: 100%;
					justify-content: center;
				}
				.el-input /deep/ .el-input__inner {
					border: 1px solid #25489C;
					border-radius: 0;
					padding: 0 10px;
					margin: 0;
					outline: none;
					color: #25489C;
					background: #fff;
					width: 100%;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
				.el-select {
					width: 100%;
				}
				.el-select /deep/ .el-input__inner {
				}
				.el-date-editor {
					width: auto;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 1px solid #25489C;
					border-radius: 0;
					padding: 0 30px;
					margin: 0;
					outline: none;
					color: #25489C;
					background: #fff;
					width: 100%;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
			}
			.list-search-btn {
				cursor: pointer;
				border: 0;
				border-radius: 0;
				padding: 0px 15px;
				margin: 0 10px 10px 0;
				outline: none;
				color: #fff;
				background: #5D62DE;
				width: auto;
				font-size: 14px;
				line-height: 42px;
				height: 42px;
				i {
					margin: 0 10px 0 0;
					color: #fff;
					font-size: 14px;
				}
			}
			.list-add-btn {
				cursor: pointer;
				border: 0;
				border-radius: 0;
				padding: 0px 15px;
				margin: 0 10px 10px 0;
				outline: none;
				color: #AD8B44;
				background: #E8D6B0;
				width: auto;
				font-size: 14px;
				line-height: 42px;
				height: 42px;
				i {
					margin: 0 10px 0 0;
					color: #fff;
					font-size: 14px;
				}
			}
		}
		.select2 {
			padding: 20px 0;
			background: none;
			width: 100%;
			height: auto;
			order: 2;
			.select2-list {
				padding: 10px 0;
				margin: 4px 0;
				background: none;
				display: flex;
				width: 100%;
				border-color: #25489C;
				border-width: 0 0 2px;
				position: relative;
				border-style: solid;
				height: auto;
				.label {
					padding: 0 10px;
					color: #25489C;
					white-space: nowrap;
					display: inline-block;
					font-size: 14px;
					line-height: 32px;
				}
				.item-body {
					display: flex;
					width: 100%;
					height: auto;
					.item {
						cursor: pointer;
						border-radius: 0;
						padding: 0 20px;
						margin: 0 2px;
						color: #25489C;
						background: none;
						font-size: 14px;
						line-height: 32px;
					}
					.item:hover {
						color: #fff;
						background: #25489C;
					}
					.item.active {
						color: #fff;
						background: #25489C;
					}
				}
			}
		}
		.sort_view {
			padding: 5px 10px 10px;
			margin: 0 0 10px;
			background: #fff;
			width: 100%;
			order: 4;
			.click-sort-btn {
				border: 0;
				border-radius: 0;
				padding: 0 15px;
				margin: 0 10px 0 0;
				background: #E8D6B0;
				.icon {
					margin: 0 2px 0 0;
					color: #25489C;
					font-size: 14px;
					line-height: 30px;
				}
				.text {
					color: #25489C;
					font-size: 14px;
					line-height: 30px;
				}
			}
		}
		.list {
			margin: 0 0 10px;
			background: none;
			width: 100%;
			order: 5;
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
				
			.index-pv1 .animation-box:hover {
				transform: rotate(0) scale(1.2) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
				
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0) scale(0.8) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list1 {
				padding: 0;
				background: none;
				display: flex;
				width: 100%;
				flex-wrap: wrap;
				height: auto;
				.list-item {
					border: 1px solid rgb(232, 214, 176);
					border-radius: 10px;
					padding: 10px;
					margin: 0 1% 20px;
					background: #fff;
					display: flex;
					width: calc(100% / 3 - 2%);
					justify-content: flex-start;
					position: relative;
					flex-wrap: wrap;
					height: auto;
					.image {
						border-radius: 10px;
						object-fit: cover;
						display: block;
						width: 200px;
						height: 100px;
					}
					.price {
						padding: 0;
						color: #FF0000;
						width: 100%;
						font-size: 14px;
						line-height: 30px;
						order: 2;
					}
					.name {
						padding: 0 10px;
						color: #000;
						font-weight: bold;
						width: calc(100% - 200px);
						font-size: 16px;
						line-height: 40px;
						text-align: center;
						order: 1;
					}
					.time_item {
						padding: 0 20px 0 0;
						width: 100%;
						order: 3;
						.icon {
							margin: 0 5px 0 0;
							color: #E0C936;
							font-size: 14px;
							line-height: 25px;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #E0C936;
							font-size: 14px;
							line-height: 25px;
						}
					}
					.publisher_item {
						padding: 0 20px 0 0;
						display: inline-block;
						order: 4;
						.icon {
							margin: 0 5px 0 0;
							color: #43BAB1;
							font-size: 14px;
							line-height: 25px;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #43BAB1;
							font-size: 14px;
							line-height: 25px;
						}
					}
					.like_item {
						padding: 0 20px 0 0;
						display: inline-block;
						order: 5;
						.icon {
							margin: 0 5px 0 0;
							color: #23469A;
							font-size: 14px;
							line-height: 25px;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #23469A;
							font-size: 14px;
							line-height: 25px;
						}
					}
					.collect_item {
						padding: 0 20px 0 0;
						display: inline-block;
						order: 6;
						.icon {
							margin: 0 5px 0 0;
							color: #AD8B44;
							font-size: 14px;
							line-height: 25px;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #AD8B44;
							font-size: 14px;
							line-height: 25px;
						}
					}
					.view_item {
						padding: 0 20px 0 0;
						display: inline-block;
						order: 8;
						.icon {
							margin: 0 5px 0 0;
							color: #A76850;
							font-size: 14px;
							line-height: 25px;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #A76850;
							font-size: 14px;
							line-height: 25px;
						}
					}
				}
			}
		}
	}
</style>
