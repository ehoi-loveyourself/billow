<template>
    <div
      class="card mb-3"
      style="max-width: 540px">
      <div class="row g-0 item">
        <div class="col-md-4">
          <img
            :src="program.poster"
            class="img-fluid rounded-start"
            alt="..." />
        </div>
        <div class="col-md-8">
          <div class="card-body">
            <div class="card-title">
              {{ program.title }}
            </div>
            <p class="card-text">
              <small
                class="text-muted">{{ program.genre }}</small>
            </p>
            <div>
              <modal :id="program.imdbID">
                <template #activator>
                  <button
                    type="button"
                    class="btn btn-outline-secondary">
                    detail
                  </button>
                </template>
              </modal>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import modal from '~/components/main/SearchModal'
  export default {
    components: {
      modal,
    },
    props: {
      movie: {
        type: Object,
        default: () => {},
      },
    },
  }
  </script>
  
  <style lang="scss" scoped>
  .card-title {
    font-size: 18px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  </style>