import * as cooperationApi from '../../api/cooperation';

/**
 * author: loser
 * version: 2017/11/28
 */
const state = {

};

const getters = {

};

const mutations = {

};

const actions = {
  getAvailableUsers({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.getAvailableUsers(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  getAvailableParticipants({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.getAvailableParticipants(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  saveCooperation({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.saveCooperation(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  updateCooperation({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.updateCooperation(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  listOwnCooperation({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.listOwnCooperation(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  listManageCooperation({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.listManageCooperation(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  listJoinCooperation({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.listJoinCooperation(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  listCooperationTree({ commit }, cooperationId) {
    return new Promise((resolve, reject) => {
      cooperationApi.listCooperationTree(cooperationId).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  getCooperation({ commit }, cooperationId) {
    return new Promise((resolve, reject) => {
      cooperationApi.getCooperation(cooperationId).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  deleteCooperation({ commit }, cooperationId) {
    return new Promise((resolve, reject) => {
      cooperationApi.deleteCooperation(cooperationId).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  listMembers({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.listMembers(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  saveMember({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.saveMember(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  changeMemberRole({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.changeMemberRole(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  deleteMember({ commit }, cooperationId) {
    return new Promise((resolve, reject) => {
      cooperationApi.deleteMember(cooperationId).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  listApplys({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.listApplys(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  doApply({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.doApply(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  dealApply({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.dealApply(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  listCooperationAgendas({ commit }, queryForm) {
    return new Promise((resolve, reject) => {
      cooperationApi.listCooperationAgendas(queryForm).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
};

export default {
  state,
  getters,
  mutations,
  actions,
};
