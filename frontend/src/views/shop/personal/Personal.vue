<template>
  <a-row :gutter="8" style="width: 100%">
    <a-col :span="6">
      <div style="background:#ECECEC; padding:30px;margin-top: 30px">
        <a-card :bordered="false">
          <b style="font-size: 15px;font-family: SimHei">店铺信息 -
            <span v-if="shopInfo != null && shopInfo.status == '0'" style="font-size: 13px;color: red;font-weight: 500">未审核</span>
            <span v-if="shopInfo != null && shopInfo.status == '1'" style="font-size: 13px;color: green;font-weight: 500">已审核</span>
          </b>
        </a-card>
        <a-card :bordered="false">
          <a-form :form="form" layout="vertical">
            <a-row :gutter="20">
              <a-col :span="12">
                <a-form-item label='店铺编号' v-bind="formItemLayout">
                  <a-input disabled v-decorator="[
                  'code',
                  { rules: [{ required: true, message: '请输入店铺编号!' }] }
                  ]"/>
                </a-form-item>
              </a-col>
              <a-col :span="12">
                <a-form-item label='店铺名称' v-bind="formItemLayout">
                  <a-input v-decorator="[
                  'name',
                  { rules: [{ required: true, message: '请输入店铺名称!' }] }
                  ]"/>
                </a-form-item>
              </a-col>
              <a-col :span="12">
                <a-form-item label='标签' v-bind="formItemLayout">
                  <a-input v-decorator="[
                  'tag',
                  { rules: [{ required: true, message: '请输入标签!' }] }
                  ]"/>
                </a-form-item>
              </a-col>
              <a-col :span="24">
                <a-form-item label='介绍' v-bind="formItemLayout">
                  <a-textarea :rows="4" v-decorator="[
                  'introduction',
                   { rules: [{ required: true, message: '请输入介绍!' }] }
                  ]"/>
                </a-form-item>
              </a-col>
              <a-col :span="24">
                <a-form-item label='照片' v-bind="formItemLayout">
                  <a-upload
                    name="avatar"
                    action="http://127.0.0.1:9527/file/fileUpload/"
                    list-type="picture-card"
                    :file-list="fileList"
                    @preview="handlePreview"
                    @change="picHandleChange"
                  >
                    <div v-if="fileList.length < 1">
                      <a-icon type="plus" />
                      <div class="ant-upload-text">
                        Upload
                      </div>
                    </div>
                  </a-upload>
                  <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
                    <img alt="example" style="width: 100%" :src="previewImage" />
                  </a-modal>
                </a-form-item>
              </a-col>
            </a-row>
          </a-form>
          <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
            修改
          </a-button>
          <a-button @click="add" style="margin-left: 15px">
            审核
          </a-button>
        </a-card>
      </div>
    </a-col>
    <a-col :span="18">
      <div style="background:#ECECEC; padding:30px;margin-top: 30px">
        <a-card :bordered="false">
          <a-spin :spinning="dataLoading">
            <a-calendar>
              <ul slot="dateCellRender" slot-scope="value" class="events">
                <li v-for="item in getListData(value)" :key="item.content">
                  <a-badge :status="item.type" :text="item.content" />
                </li>
              </ul>
            </a-calendar>
          </a-spin>
        </a-card>
      </div>
    </a-col>
    <auditAdd
      v-if="commodityAdd.visiable"
      @close="handlecommodityAddClose"
      @success="handlecommodityAddSuccess"
      :commodityAddVisiable="commodityAdd.visiable">
    </auditAdd>
  </a-row>
</template>

<script>
import {mapState} from 'vuex'
import moment from 'moment'
import AuditAdd from './AuditAdd.vue'
moment.locale('zh-cn')
const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
function getBase64 (file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}
export default {
  name: 'User',
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    })
  },
  components: {AuditAdd},
  data () {
    return {
      commodityAdd: {
        visiable: false
      },
      rowId: null,
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      fileList: [],
      previewVisible: false,
      previewImage: '',
      courseInfo: [],
      dataLoading: false,
      classList: [],
      shopInfo: null
    }
  },
  mounted () {
    this.dataInit()
  },
  methods: {
    moment,
    add () {
      this.commodityAdd.visiable = true
    },
    handlecommodityAddClose () {
      this.commodityAdd.visiable = false
    },
    handleCancel () {
      this.previewVisible = false
    },
    handlecommodityAddSuccess () {
      this.commodityAdd.visiable = false
      this.$message.success('新增审核成功')
      this.search()
    },
    async handlePreview (file) {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj)
      }
      this.previewImage = file.url || file.preview
      this.previewVisible = true
    },
    picHandleChange ({ fileList }) {
      this.fileList = fileList
    },
    isDuringDate (beginDateStr, endDateStr, curDataStr) {
      let curDate = new Date(curDataStr)
      let beginDate = new Date(beginDateStr)
      let endDate = new Date(endDateStr)
      if (curDate >= beginDate && curDate <= endDate) {
        return true
      }
      return false
    },
    getListData (value) {
      let listData = []
      this.courseInfo.forEach(item => {
        if ((moment(value).format('YYYY-MM-DD')) === (moment(item.createDate).format('YYYY-MM-DD'))) {
          listData.push({type: 'success', content: '✔'})
        }
      })
      return listData || []
    },
    dataInit () {
      this.dataLoading = true
      this.$get(`/cos/shop-info/detail/${this.currentUser.userId}`).then((r) => {
        this.rowId = r.data.user.id
        this.shopInfo = r.data.user
        this.setFormValues(r.data.user)
        this.courseInfo = r.data.order
        this.dataLoading = false
      })
    },
    setFormValues ({...user}) {
      this.rowId = user.id
      let fields = ['introduction', 'code', 'introduction', 'tag', 'name', 'images']
      let obj = {}
      Object.keys(user).forEach((key) => {
        if (key === 'images' && user[key] !== null) {
          this.fileList = []
          this.imagesInit(user['images'])
        }
        if (fields.indexOf(key) !== -1) {
          this.form.getFieldDecorator(key)
          obj[key] = user[key]
        }
      })
      this.form.setFieldsValue(obj)
    },
    imagesInit (images) {
      if (images !== null && images !== '') {
        let imageList = []
        images.split(',').forEach((image, index) => {
          imageList.push({uid: index, name: image, status: 'done', url: 'http://127.0.0.1:9527/imagesWeb/' + image})
        })
        this.fileList = imageList
      }
    },
    handleSubmit () {
      // 获取图片List
      let images = []
      this.fileList.forEach(image => {
        if (image.response !== undefined) {
          images.push(image.response)
        } else {
          images.push(image.name)
        }
      })
      this.form.validateFields((err, values) => {
        values.id = this.rowId
        values.images = images.length > 0 ? images.join(',') : null
        if (!err) {
          this.loading = true
          this.$put('/cos/shop-info', {
            ...values
          }).then((r) => {
            this.$message.success('修改信息成功')
            this.loading = false
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>
</style>
