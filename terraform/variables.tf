variable "project_id" {
  type = string
}

variable "region" {
  type = string
  default = "europe-west1"
}

variable "container_image" {
  type = string
  description = "Artifact Registry image URL"
}

variable "google_routes_api_key" {
  type      = string
  sensitive = true
}
